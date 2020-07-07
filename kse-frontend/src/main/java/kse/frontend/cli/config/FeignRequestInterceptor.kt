package kse.frontend.cli.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
object FeignRequestInterceptor : RequestInterceptor {

    override fun apply(template: RequestTemplate) {
        template.header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46YWRtaW4=") // admin:admin
        template.header(HttpHeaders.USER_AGENT, "OpenFeign")
        template.header(HttpHeaders.ACCEPT, "application/json;charset=UTF-8")
        template.header(HttpHeaders.ACCEPT_ENCODING, "UTF-8")
        template.header(HttpHeaders.ACCEPT_CHARSET, "UTF-8")
        template.header(HttpHeaders.ACCEPT_LANGUAGE, "en-US,zh-CN")
    }

}
