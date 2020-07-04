package kse.frontend.cli

import com.github.yingzhuo.carnival.openfeign.FeignClient
import feign.Param
import feign.RequestLine

@FeignClient(url = "\${svc.backend}")
interface UtilityClient {

    @RequestLine("GET /utility/uuid?n={n}")
    fun uuid(@Param("n") n: Int): List<String>

}
