package kse.backend.service

import com.github.yingzhuo.carnival.exception.business.BusinessException
import kse.backend.prometheus.Prometheus
import org.springframework.stereotype.Service
import java.util.*

@Service
open class UtilityServiceImpl(private val prometheus: Prometheus) : UtilityService {

    override fun uuid(n: Int, short: Boolean): List<String> {
        val list = mutableListOf<String>()
        if (n > 0) {
            for (i in 1..n) {
                list.add(UUID.randomUUID().toString())
            }
        }

        try {
            return if (short)
                list.map { it.replace("-", "") }
            else
                list
        } finally {
            prometheus.uuidCreated(n)
        }
    }

    override fun snowflake(n: Int): List<String> = throw BusinessException.of("000")

}
