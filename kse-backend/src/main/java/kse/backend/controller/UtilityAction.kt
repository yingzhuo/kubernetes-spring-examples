package kse.backend.controller

import kse.common.tool.UUIDs
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("utility")
open class UtilityAction {

    @GetMapping("uuid")
    open fun uuid(@RequestParam("n", defaultValue = "1") n: Int): List<String> =
            UUIDs.createRandom(n)

}
