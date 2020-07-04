package kse.backend.prometheus

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
open class Prometheus(registry: MeterRegistry) {

    companion object {
        private val log = LoggerFactory.getLogger(Prometheus::class.java)
    }

    private val uuidCreationCounter: Counter = Counter.builder("uuid_creation").register(registry)

    init {
        log.info("registry type = {}", registry.javaClass.name)
    }

    open fun uuidCreated(n: Int) = uuidCreationCounter.increment(n.toDouble())

}
