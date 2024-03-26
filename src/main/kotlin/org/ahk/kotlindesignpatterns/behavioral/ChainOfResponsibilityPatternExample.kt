package org.ahk.kotlindesignpatterns.behavioral

enum class EventType {
    TYPE_1,
    TYPE_2,
    TYPE_3
}

interface Handler {
    fun handle(eventType: EventType): Boolean
    fun nextHandler(): Handler?
}

class Handler1 : Handler {
    override fun handle(eventType: EventType): Boolean {
        return when (eventType) {
            EventType.TYPE_1 -> {
                println("Event $eventType handled by Handler1")
                true
            }

            else -> nextHandler().handle(eventType)
        }
    }

    override fun nextHandler() = Handler2()
}

class Handler2 : Handler {
    override fun handle(eventType: EventType): Boolean {
        return when (eventType) {
            EventType.TYPE_2 -> {
                println("Event $eventType handled by Handler2")
                true
            }

            else -> nextHandler().handle(eventType)
        }
    }

    override fun nextHandler() = Handler3()
}

class Handler3 : Handler {
    override fun handle(eventType: EventType): Boolean {
        return when (eventType) {
            EventType.TYPE_3 -> {
                println("Event $eventType handled by Handler3")
                true
            }

            else -> false
        }
    }

    override fun nextHandler() = null
}


fun main(args: Array<String>) {
    val handler = Handler1()
    listOf(EventType.TYPE_3, EventType.TYPE_1, EventType.TYPE_2)
            .forEach { event -> handler.handle(event) }
}