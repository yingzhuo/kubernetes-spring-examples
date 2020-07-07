package kse.frontend

import kse.frontend.config.PortConfig
import org.apache.catalina.connector.Connector
import org.slf4j.LoggerFactory
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppTomcat(private val ports: PortConfig) {

    companion object {
        private val log = LoggerFactory.getLogger(AppTomcat::class.java)
        private const val protocol = "org.apache.coyote.http11.Http11NioProtocol"
    }

    @Bean
    open fun tomcatServletWebServerFactory(): TomcatServletWebServerFactory {

        log.debug("http-port: {}", ports.http)
        log.debug("http-ports: {}", ports.https)

        // 嵌套函数
        fun connector(): Connector {
            val connector = Connector(protocol)
            connector.scheme = "http"
            connector.port = ports.http
            return connector
        }

        val bean = TomcatServletWebServerFactory()
        bean.addAdditionalTomcatConnectors(connector())
        return bean
    }

}
