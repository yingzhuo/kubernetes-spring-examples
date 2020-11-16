package kse.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.yingzhuo.carnival.openfeign.EnableFeignClients
import feign.codec.{Decoder, Encoder}
import feign.jackson.{JacksonDecoder, JacksonEncoder}
import feign.micrometer.MicrometerCapability
import feign.{Capability, Contract}
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
@EnableFeignClients
protected class ApplicationBootFeign {

  @Bean
  def contract(): Contract = new Contract.Default()

  @Bean
  def encoder(om: ObjectMapper): Encoder = new JacksonEncoder(om)

  @Bean
  def decoder(om: ObjectMapper): Decoder = new JacksonDecoder(om)

  @Bean
  def capability(registry: MeterRegistry): Capability = new MicrometerCapability(registry)

}
