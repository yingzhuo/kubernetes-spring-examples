package kse.frontend

import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ServletComponentScan
open class AppWeb : WebMvcConfigurer
