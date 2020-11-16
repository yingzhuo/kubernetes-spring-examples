package kse.frontend.cli

import com.github.yingzhuo.carnival.openfeign.FeignClient
import feign.{Param, RequestLine}
import io.github.resilience4j.retry.annotation.Retry

@FeignClient(url = "${svc.backend}")
trait UUIDClient {

  @Retry(name = "backend")
  @RequestLine("GET /uuid?n={n}")
  def uuid(@Param("n") n: Int): Array[String]

}
