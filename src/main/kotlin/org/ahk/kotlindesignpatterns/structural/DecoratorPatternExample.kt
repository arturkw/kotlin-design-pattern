package org.ahk.kotlindesignpatterns.structural

import kotlin.random.Random

class RandomElementListDecorator<E>(private val list: MutableList<E>) {

    fun add(e: E): Boolean = list.add(e)

    fun get(index: Int): E = list[index]

    fun getRandomElement(): E = list[Random.nextInt(0, list.size)]

}

fun main(args: Array<String>) {

    val r = RandomElementListDecorator(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    for (i in 1..10000000) {
        println(r.getRandomElement())
    }

}