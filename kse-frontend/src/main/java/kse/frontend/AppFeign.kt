package kse.frontend

import com.fasterxml.jackson.module.kotlin.KotlinModule
import feign.Feign
import feign.Logger
import feign.codec.Decoder
import feign.codec.Encoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import kse.frontend.cli.UtilityClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppFeign {

    @Value("\${svc.backend}")
    lateinit var backendSvcUrl: String

    @Value("\${svc.backend}")
    lateinit var frontendSvcUrl: String

    @Bean
    open fun utilityClient(): UtilityClient {
        return Feign.builder()
                .encoder(encoder())
                .decoder(decoder())
                .logLevel(Logger.Level.FULL)
                .logger(Slf4jLogger("OpenFeign"))
                .target(UtilityClient::class.java, backendSvcUrl)
    }

    private fun encoder(): Encoder = JacksonEncoder(listOf(KotlinModule()))

    private fun decoder(): Decoder = JacksonDecoder(listOf(KotlinModule()))

}
