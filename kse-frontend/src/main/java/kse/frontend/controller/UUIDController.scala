package kse.frontend.controller

import kse.frontend.cli.UUIDClient
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

@RestController
protected class UUIDController private(cli: UUIDClient) {

  @GetMapping(path = Array("/uuid"))
  def uuid(@RequestParam(name = "n", defaultValue = "1") n: Int): Array[String] = cli.uuid(n)

}
