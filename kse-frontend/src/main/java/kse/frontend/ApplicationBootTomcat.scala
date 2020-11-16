package kse.frontend

import kse.frontend.config.PortConfig
import org.apache.catalina.connector.Connector
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
protected class ApplicationBootTomcat(port: PortConfig) {

  @Bean
  def tomcatServletWebServerFactory(): TomcatServletWebServerFactory = {
    val connector = new Connector("org.apache.coyote.http11.Http11NioProtocol")
    connector.setScheme("http")
    connector.setPort(port.http)

    val bean = new TomcatServletWebServerFactory()
    bean.addAdditionalTomcatConnectors(connector)
    bean
  }

}
