package org.ahk.kotlindesignpatterns.structural

import org.ahk.kotlindesignpatterns.creational.Cache
import org.ahk.kotlindesignpatterns.creational.CacheV2

interface ExternalApi {
    fun staticData(key: String): String
}

class StaticDataExternalApiImpl : ExternalApi {

    override fun staticData(key: String): String {
        println("Calling static data service. Key = $key")
        return when (key) {
            "1" -> "one"
            "2" -> "two"
            "3" -> "three"
            else -> "default"
        }
    }

}

class ExternalApiProxy(private val externalApi: ExternalApi, private val cache: Cache) : ExternalApi {

    override fun staticData(key: String): String {
        val value: String? = cache.get(key)
        return if (value == null) {
            val staticData = externalApi.staticData(key)
            cache.add(key, staticData)
            staticData
        } else {
            value
        }
    }

}

fun main(args: Array<String>) {
    val eap = ExternalApiProxy(StaticDataExternalApiImpl(), CacheV2)
    eap.staticData("2")
    eap.staticData("2")
    eap.staticData("2")
}
