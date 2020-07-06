package kse.backend.service

interface UtilityService {

    fun uuid(n: Int = 1, short: Boolean = false): List<String>

}
