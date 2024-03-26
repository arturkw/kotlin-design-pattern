package org.ahk.kotlindesignpatterns.creational

interface Car {
    fun color(): String
    fun drive(): Any
}

class SportCar private constructor(private val color: String) : Car {
    companion object Factory : CarFactory() {
        override fun buildCar(color: String) = SportCar(color)
    }

    override fun color() = color

    override fun drive() = println("Super fast $color car")
}

class LimousineCar private constructor(private val color: String) : Car {
    companion object Factory : CarFactory() {
        override fun buildCar(color: String) = LimousineCar(color)
    }

    override fun color() = color

    override fun drive() = println("Very elegant $color car")

}

abstract class CarFactory {
    abstract fun buildCar(color: String): Car
}

fun main(args: Array<String>) {
    val limousineCar = LimousineCar.buildCar("black")
    limousineCar.drive()
    val sportCar = SportCar.buildCar("green")
    sportCar.drive()
}