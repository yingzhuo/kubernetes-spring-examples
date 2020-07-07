package kse.frontend

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.config.MeterFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppPrometheus {

    @Value("\${management.endpoints.web.base-path:/actuator}")
    lateinit var actuatorPrefix: String

    @Bean
    open fun timedAspect(): TimedAspect = TimedAspect()

    @Bean
    open fun meterRegistryCustomizer(): MeterRegistryCustomizer<MeterRegistry> {
        return MeterRegistryCustomizer { it: MeterRegistry ->
            it.config()
                    .commonTags("app", "kubernetes-spring-examples")
                    .commonTags("tier", "frontend")
                    .meterFilter(MeterFilter.deny { id: Meter.Id ->
                        val uri = id.getTag("uri")
                        uri != null && uri.startsWith(actuatorPrefix)
                    })
                    .meterFilter(MeterFilter.deny { id: Meter.Id ->
                        val uri = id.getTag("uri")
                        uri != null && uri.contains("favicon")
                    })
        }
    }

}
