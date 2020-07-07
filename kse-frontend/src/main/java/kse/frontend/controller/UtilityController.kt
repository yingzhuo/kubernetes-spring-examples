package kse.frontend.controller

import com.github.yingzhuo.carnival.json.Json
import kse.frontend.cli.UtilityClient
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Validated
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
            @Min(1) @Max(100) @RequestParam("n", defaultValue = "1") n: Int,
            @RequestParam("short", defaultValue = "false") short: Boolean
    ): Json {
        log.debug("n = {}", n)
        log.debug("short = {}", short)
        val results = utilityClient.uuid(n, short)
        return Json.newInstance()
                .payload("results", results)
                .payload("n", n)
    }

    @GetMapping("snowflake")
    open fun snowflake(
            @Min(1) @Max(100) @RequestParam("n", defaultValue = "1") n: Int
    ): Json {
        log.debug("n = {}", n)
        val results = utilityClient.snowflake(n)
        return Json.newInstance()
                .payload("results", results)
                .payload("n", n)
    }

}
