package kse.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
open class AppJson {

    @Autowired(required = false)
    open fun resetObjectMapper(om: ObjectMapper) {
        Optional.ofNullable(om).ifPresent {
            it.registerModule(KotlinModule())
        }
    }

}
