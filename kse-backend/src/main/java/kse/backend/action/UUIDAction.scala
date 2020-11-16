package kse.backend.action

import kse.backend.service.UUIDService
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

@RestController
protected class UUIDAction private(service: UUIDService) {

  @GetMapping(path = Array("/uuid"))
  def uuid(@RequestParam("n") n: Int): Array[String] = service.uuid(n)

}
