package kse.frontend.controller.adv

import com.github.yingzhuo.carnival.exception.business.BusinessException
import com.github.yingzhuo.carnival.json.Json
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.io.UncheckedIOException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
object ExceptionHandlers {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    fun handleBusinessException(ex: BusinessException): Json {
        return Json.newInstance()
                .code(ex.code)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(ex: ConstraintViolationException): Json {
        return Json.newInstance()
                .code("400")
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun handleUncheckedIOException(ex: UncheckedIOException): Json {
        return Json.newInstance()
                .code("503")
    }

}
