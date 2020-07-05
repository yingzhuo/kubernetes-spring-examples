package kse.frontend

import com.github.yingzhuo.carnival.openfeign.EnableFeignClients
import feign.Capability
import feign.Contract
import feign.RequestInterceptor
import feign.RequestTemplate
import feign.micrometer.MicrometerCapability
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Configuration
@EnableFeignClients
open class AppFeign {

    @Bean
    open fun contract(): Contract = Contract.Default()

    @Bean
    open fun capability(registry: MeterRegistry): Capability = MicrometerCapability(registry)

    @Component
    object FeignGlobalRequestInterceptor : RequestInterceptor {
        override fun apply(template: RequestTemplate) {
            template.header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46YWRtaW4=")
            template.header(HttpHeaders.USER_AGENT, "OpenFeign")
        }
    }

}
