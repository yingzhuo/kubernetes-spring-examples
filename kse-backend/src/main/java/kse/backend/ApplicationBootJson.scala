package kse.backend

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
protected class ApplicationBootJson {

  @Autowired(required = false)
  def configScalaModules(om: ObjectMapper): Unit = Option(om).foreach(_.registerModules(DefaultScalaModule))

}
