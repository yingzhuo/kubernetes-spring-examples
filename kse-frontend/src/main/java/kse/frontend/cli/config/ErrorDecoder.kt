package kse.frontend.cli.config

import com.github.yingzhuo.carnival.exception.business.BusinessException
import com.github.yingzhuo.carnival.openfeign.error.AbstractErrorDecoder
import com.github.yingzhuo.carnival.spring.JacksonUtils
import org.springframework.stereotype.Component
import java.io.Reader
import java.util.*

@Component
object ErrorDecoder : AbstractErrorDecoder(), feign.codec.ErrorDecoder {

    override fun decode(methodKey: String, status: Int, body: Reader, headers: MutableMap<String, MutableCollection<String>>): Optional<Exception> {
        if (status == 400) {
            val info = JacksonUtils.readValue(body, Map::class.java)
            if (info["type"] == BusinessException::class.java.name) {
                return Optional.of(
                        BusinessException(
                                info["code"] as String,
                                info["message"] as String
                        )
                )
            }
        }
        return Optional.empty()
    }

}
