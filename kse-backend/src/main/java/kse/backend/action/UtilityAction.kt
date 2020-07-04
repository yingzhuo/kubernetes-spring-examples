package kse.backend.action

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
    open fun uuid(
            @RequestParam("n", defaultValue = "1") n: Int,
            @RequestParam("short", defaultValue = "false") short: Boolean
    ): List<String> {
        try {
            return UUIDs.createRandom(n, short)
        } finally {
            prometheus.uuidCreated(n)
        }
    }

}
