package kse.backend

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
open class AppMeter {

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
        }
    }

}
