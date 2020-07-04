package kse.backend

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.config.MeterFilter
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
open class AppPrometheus {

    @Bean
    open fun timedAspect(): TimedAspect {
        return TimedAspect()
    }

    @Bean
    open fun meterRegistryCustomizer(): MeterRegistryCustomizer<MeterRegistry> {
        return MeterRegistryCustomizer { it: MeterRegistry ->
            it.config()
                    .commonTags("app", "kubernetes-spring-examples")
                    .commonTags("tier", "backend")
                    .meterFilter(MeterFilter.deny { id: Meter.Id ->
                        val uri = id.getTag("uri")
                        uri != null && uri.startsWith("/actuator")
                    })
                    .meterFilter(MeterFilter.deny { id: Meter.Id ->
                        val uri = id.getTag("uri")
                        uri != null && uri.contains("favicon")
                    })
        }
    }

}
