package kse.frontend.cli.config

import feign.{RequestInterceptor, RequestTemplate}
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
protected class HeadersConfigurationInterceptor extends AnyRef with RequestInterceptor {

  override def apply(template: RequestTemplate): Unit = {
    template.header(HttpHeaders.USER_AGENT, "OpenFeign")
    template.header(HttpHeaders.ACCEPT, "application/json;charset=UTF-8")
    template.header(HttpHeaders.ACCEPT_ENCODING, "UTF-8")
    template.header(HttpHeaders.ACCEPT_CHARSET, "UTF-8")
    template.header(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN")
    template.header(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
  }

}
