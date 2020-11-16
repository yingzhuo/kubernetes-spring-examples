package kse.frontend.config

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.context.properties.{ConfigurationProperties, ConstructorBinding}

import scala.beans.BeanProperty

@ConstructorBinding
@ConfigurationProperties(prefix = "port")
case class PortConfig(@BeanProperty http: Int, @BeanProperty https: Int) extends AnyRef
  with InitializingBean {

  protected val log: Logger = LoggerFactory.getLogger(getClass)

  override def afterPropertiesSet(): Unit = {
    log.debug("http port = {}", http)
    log.debug("https port = {}", https)
  }

}
