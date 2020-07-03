package kse.common.tool

import java.util.*

object UUIDs {

    fun createRandom(n: Int = 1, short: Boolean = false): List<String> {
        val list = mutableListOf<String>()
        if (n >= 1) {
            for (i in 1..n) {
                var uuid = UUID.randomUUID().toString()
                if (short) {
                    uuid = uuid.replace("-", "")
                }
                list.add(uuid)
            }
        }
        return list
    }

}
