package kse.backend.action.adv

import com.github.yingzhuo.carnival.exception.business.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

@RestControllerAdvice(basePackages = Array("kse.backend.action"))
protected class ErrorHandlers {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  def handle(e: BusinessException): String = s"@@${e.getCode}@@${e.getMessage}@@${e.getClass.getName}"

}
