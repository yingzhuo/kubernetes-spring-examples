package kse.backend.action

import kse.backend.service.UtilityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("utility")
open class UtilityAction(
        private val utilityService: UtilityService
) {

    @GetMapping("uuid")
    open fun uuid(
            @RequestParam("n", defaultValue = "1") n: Int,
            @RequestParam("short", defaultValue = "false") short: Boolean
    ): List<String> {
        return utilityService.uuid(n, short)
    }

    @GetMapping("snowflake")
    open fun snowflake(
            @RequestParam("n", defaultValue = "1") n: Int
    ): List<String> {
        return utilityService.snowflake(n)
    }

}
