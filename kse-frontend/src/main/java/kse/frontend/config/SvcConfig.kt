package kse.frontend.config

import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.annotation.PostConstruct

@ConstructorBinding
@ConfigurationProperties("svc")
data class SvcConfig(
        val backend: String,
        val frontend: String
) {

    companion object {
        private val log = LoggerFactory.getLogger(SvcConfig::class.java)
    }

    @PostConstruct
    fun init() {
        log.debug("backend-url: {}", backend)
        log.debug("frontend-url: {}", frontend)
    }

}
