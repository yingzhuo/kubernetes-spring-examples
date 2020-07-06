package kse.backend.action.adv

import com.github.yingzhuo.carnival.exception.business.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
object ExceptionHandlers {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBusinessException(ex: BusinessException): Map<String, Any?> {
        return ex.asMap()
    }

}
