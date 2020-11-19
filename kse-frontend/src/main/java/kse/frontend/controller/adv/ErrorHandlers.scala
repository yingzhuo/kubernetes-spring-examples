package kse.frontend.controller.adv

import com.github.yingzhuo.carnival.exception.business.BusinessException
import com.github.yingzhuo.carnival.json.Json
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

@RestControllerAdvice
protected class ErrorHandlers {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.OK)
  def handle(e: BusinessException): Json =
    Json.newInstance()
      .code(e.getCode)
      .errorMessage(e.getMessage)

}
