package kse.backend.service

import org.springframework.stereotype.Service
import java.util.*

@Service
open class UtilityServiceImpl : UtilityService {

    override fun uuid(n: Int, short: Boolean): List<String> {
        val list = mutableListOf<String>()
        if (n > 0) {
            for (i in 1..n) {
                list.add(UUID.randomUUID().toString())
            }
        }

        return if (short)
            list.map { it.replace("-", "") }
        else
            list
    }
}