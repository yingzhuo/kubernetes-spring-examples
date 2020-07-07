package kse.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.yingzhuo.carnival.openfeign.EnableFeignClients
import feign.Capability
import feign.Contract
import feign.codec.Decoder
import feign.codec.Encoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
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
    open fun encoder(om: ObjectMapper): Encoder = JacksonEncoder(om)

    @Bean
    open fun decoder(om: ObjectMapper): Decoder = JacksonDecoder(om)

    @Bean
    open fun capability(registry: MeterRegistry): Capability = MicrometerCapability(registry)

}
