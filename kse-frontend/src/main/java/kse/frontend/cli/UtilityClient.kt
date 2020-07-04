package kse.frontend.cli

import com.github.yingzhuo.carnival.openfeign.FeignClient
import feign.Param
import feign.RequestLine
import io.github.resilience4j.retry.annotation.Retry

@FeignClient(url = "\${svc.backend}")
interface UtilityClient {

    @Retry(name = "backend")
    @RequestLine("GET /utility/uuid?n={n}&short={short}")
    fun uuid(@Param("n") n: Int, @Param("short") short: Boolean = false): List<String>

}
