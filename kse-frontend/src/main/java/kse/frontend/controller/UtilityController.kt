package kse.frontend.controller

import kse.frontend.cli.UtilityClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("utility")
open class UtilityController @Autowired() constructor(private val utilityClient: UtilityClient) {

    @GetMapping("uuid")
    open fun uuid(@RequestParam("n", defaultValue = "1") n: Int): List<String> = utilityClient.uuid(n)

}
