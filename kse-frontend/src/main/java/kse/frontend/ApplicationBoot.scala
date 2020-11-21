package kse.frontend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
private class ApplicationBoot

object ApplicationBoot extends App {
  SpringApplication.run(classOf[ApplicationBoot], args: _*)
}
