package kse.backend.controller

import kse.backend.prometheus.Prometheus
import kse.backend.tool.UUIDs
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("utility")
open class UtilityAction(private val prometheus: Prometheus) {

    @GetMapping("uuid")
    open fun uuid(@RequestParam("n", defaultValue = "1") n: Int): List<String> {
        try {
            return UUIDs.createRandom(n)
        } finally {
            prometheus.uuidCreated(n)
        }
    }

}
