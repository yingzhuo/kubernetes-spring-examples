package kse.frontend.controller

import kse.frontend.cli.BrokenClient
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
protected class BrokenController(brokenClient: BrokenClient) {

  @GetMapping(Array("/broken"))
  def broken(): String = {
    brokenClient.broken()
    "ok"
  }

}
