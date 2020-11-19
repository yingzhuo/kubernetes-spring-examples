package kse.backend.action

import com.github.yingzhuo.carnival.exception.business.BusinessException
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
protected class BrokenAction() {

  @GetMapping(Array("/broken"))
  def broken(): Nothing = throw BusinessException.of("000")

}
