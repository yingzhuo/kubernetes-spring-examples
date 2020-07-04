package kse.frontend.controller

import kse.frontend.cli.UtilityClient
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("utility")
open class UtilityController(
        private val utilityClient: UtilityClient
) {

    companion object {
        private val log = LoggerFactory.getLogger(UtilityController::class.java)
    }

    @GetMapping("uuid")
    open fun uuid(
            @RequestParam("n", defaultValue = "1") n: Int,
            @RequestParam("short", defaultValue = "false") short: Boolean
    ): List<String> {
        log.debug("n = {}", n)
        log.debug("short = {}", short)
        return utilityClient.uuid(n, short)
    }

}
