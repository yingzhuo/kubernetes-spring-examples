package kse.frontend

import org.apache.catalina.connector.Connector
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppTomcat(@Value("\${port.http}") private val httpPort: Int,
                     @Value("\${port.https}") private val httpsPort: Int) {

    companion object {
        private val log = LoggerFactory.getLogger(AppTomcat::class.java)
    }

    @Bean
    open fun tomcatServletWebServerFactory(): TomcatServletWebServerFactory {

        log.debug("http-port: {}", httpPort)
        log.debug("http-ports: {}", httpsPort)

        // 嵌套函数
        fun connector(): Connector {
            val connector = Connector("org.apache.coyote.http11.Http11NioProtocol")
            connector.scheme = "http"
            connector.port = httpPort
            return connector
        }

        val bean = TomcatServletWebServerFactory()
        bean.addAdditionalTomcatConnectors(connector())
        return bean
    }
}
