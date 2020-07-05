package kse.frontend

import com.github.yingzhuo.carnival.openfeign.EnableFeignClients
import feign.Capability
import feign.Contract
import feign.RequestInterceptor
import feign.auth.BasicAuthRequestInterceptor
import feign.micrometer.MicrometerCapability
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients
open class AppFeign {

    @Bean
    open fun contract(): Contract = Contract.Default()

    @Bean
    open fun capability(registry: MeterRegistry): Capability = MicrometerCapability(registry)

    @Bean
    open fun httpBasicInterceptor(): RequestInterceptor =
            BasicAuthRequestInterceptor("admin", "admin")

}
