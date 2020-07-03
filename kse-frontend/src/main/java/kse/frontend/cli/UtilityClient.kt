package kse.frontend.cli

import feign.Param
import feign.RequestLine

interface UtilityClient {

    @RequestLine("GET /utility/uuid?n={n}")
    fun uuid(@Param("n") n: Int): List<String>

}
