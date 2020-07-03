package kse.backend.prometheus

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
open class Prometheus(private val registry: MeterRegistry): InitializingBean {

    override fun afterPropertiesSet() {
    }

}
