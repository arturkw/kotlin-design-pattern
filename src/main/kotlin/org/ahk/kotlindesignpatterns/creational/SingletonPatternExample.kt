package org.ahk.kotlindesignpatterns.creational

interface Cache {
    fun add(key: String, value: String)
    fun get(key: String): String?
}

class CacheV1 private constructor() : Cache {
    private val keyValueStore = mutableMapOf<String, String>()

    companion object {
        private val instance: CacheV1 by lazy {
            CacheV1()
        }

        fun getCache() = instance
    }

    override fun add(key: String, value: String) {
        println("Add key=$key, value=$value")
        keyValueStore[key] = value
    }

    override fun get(key: String): String? {
        println("Return value=${keyValueStore[key]} for key=$key")
        return keyValueStore[key]
    }
}

object CacheV2 : Cache {
    private val keyValueStore = mutableMapOf<String, String>()

    override fun add(key: String, value: String) {
        println("Add key=$key, value=$value")
        keyValueStore[key] = value
    }

    override fun get(key: String): String? {
        println("Return value=${keyValueStore[key]} for key=$key")
        return keyValueStore[key]
    }
}

fun main(args: Array<String>) {
    val cacheV1 = CacheV1.getCache()
    cacheV1.add("one", "1")
    cacheV1.add("two", "2")
    cacheV1.add("three", "3")
    val one = cacheV1.get("one")

    val cacheV2 = CacheV2
    CacheV2.add("one", "1")
    CacheV2.add("two", "2")
    CacheV2.add("three", "3")
    val two = CacheV2.get("two")
}