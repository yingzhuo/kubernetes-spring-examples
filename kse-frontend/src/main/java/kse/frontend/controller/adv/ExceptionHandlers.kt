package kse.frontend.controller.adv

import com.github.yingzhuo.carnival.exception.business.BusinessException
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
    fun handleBusinessException(ex: BusinessException): Map<String, Any> = ex.asMap()

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(ex: ConstraintViolationException): Map<String, Any> {
        return mapOf(
                "code" to "400"
        )
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun handleUncheckedIOException(ex: UncheckedIOException): Map<String, Any> {
        return mapOf(
                "code" to "501"
        )
    }

}
