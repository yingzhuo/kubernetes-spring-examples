package kse.backend.action.adv

import com.github.yingzhuo.carnival.exception.business.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

import scala.collection.mutable
import scala.jdk.CollectionConverters._

@RestControllerAdvice
protected class ErrorHandler {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  def handler(e: BusinessException): mutable.Map[String, AnyRef] = e.asMap().asScala

}
