package org.ahk.kotlindesignpatterns.behavioral

interface Event
data class Event1(val data: String) : Event
data class Event2(val data: String) : Event

interface Observer {
    fun handle(event: Event)
}

class Observer1 : Observer {
    override fun handle(event: Event) = println("Observer 1 informed about $event")
}

class Observer2 : Observer {
    override fun handle(event: Event) = println("Observer 2 informed about $event")
}

class Observer3 : Observer {
    override fun handle(event: Event) = println("Observer 3 informed about $event")
}

class Publisher {

    private val subscribers = mutableListOf<Observer>()

    fun addSubscriber(observer: Observer) = subscribers.add(observer)

    fun removeSubscriber(observer: Observer) = subscribers.remove(observer)

    fun notify(event: Event) = subscribers.forEach { o -> o.handle(event) }
}

fun main(args: Array<String>) {

    val publisher = Publisher()
    publisher.addSubscriber(Observer1())
    publisher.addSubscriber(Observer2())
    publisher.addSubscriber(Observer3())

    listOf(Event1("text"), Event2("image"), Event1("2"),
            Event2("Random!23"), Event2("123.30"))
            .forEach { e -> publisher.notify(e) }

}