package kse.frontend.prometheus

import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
open class Prometheus(registry: MeterRegistry) {

    companion object {
        private val log = LoggerFactory.getLogger(Prometheus::class.java)
    }

    init {
        log.info("registry type = {}", registry.javaClass.name)
    }

}
