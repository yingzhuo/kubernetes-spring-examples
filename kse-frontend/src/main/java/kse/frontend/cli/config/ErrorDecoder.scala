package kse.frontend.cli.config

import java.io.Reader
import java.util
import java.util.Optional

import com.github.yingzhuo.carnival.exception.business.BusinessException
import com.github.yingzhuo.carnival.openfeign.error.AbstractErrorDecoder
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Component

@Component
protected class ErrorDecoder extends AbstractErrorDecoder {

  override def decode(methodKey: String, status: Int, body: Reader, headers: util.Map[String, util.Collection[String]]): Optional[Exception] = {

    if (status != 503) {
      return Optional.empty()
    }

    IOUtils.toString(body) match {
      case ErrorDecoder.ExceptionPattern(code, message, exceptionType) =>
        Optional.of(newException(code, message, exceptionType))
      case _ =>
        Optional.empty();
    }
  }

  private def newException(code: String, message: String, exceptionType: String): Exception =
    exceptionType match {
      case _ if exceptionType == classOf[BusinessException].getName =>
        BusinessException.of(code, message)
      case _ =>
        null
    }

}


protected object ErrorDecoder {
  private val ExceptionPattern = "@@(.+)@@(.+)@@(.+)".r
}
