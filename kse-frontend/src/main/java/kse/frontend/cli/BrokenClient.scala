package kse.frontend.cli

import com.github.yingzhuo.carnival.openfeign.FeignClient
import feign.RequestLine
import io.github.resilience4j.retry.annotation.Retry

@FeignClient(url = "${svc.backend}")
trait BrokenClient {

  @Retry(name = "backend")
  @RequestLine("GET /broken")
  def broken(): Nothing

}
