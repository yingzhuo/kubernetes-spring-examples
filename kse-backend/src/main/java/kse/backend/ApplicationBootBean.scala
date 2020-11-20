package kse.backend

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.{Configuration, ImportResource}

@Configuration
@ConfigurationPropertiesScan(Array(kse.BasePackageName))
@ImportResource(Array(
  "classpath*:/spring/*.xml"
))
protected class ApplicationBootBean