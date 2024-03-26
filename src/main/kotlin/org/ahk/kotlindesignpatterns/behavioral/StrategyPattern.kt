package org.ahk.kotlindesignpatterns.behavioral

interface SortStrategy<E> {
    fun sort(elements: List<E>)
}

class InsertionSort<E> : SortStrategy<E> {
    override fun sort(elements: List<E>) {
        TODO("Implement insertion sort algorithm")
    }
}

class BubbleSort<E> : SortStrategy<E> {
    override fun sort(elements: List<E>) {
        TODO("Implement bubble sort algorithm")
    }
}

fun main(args: Array<String>) {
    fun <E> sortList(elements: List<E>, strategy: SortStrategy<E>) = strategy.sort(elements)

    sortList(listOf("1", "2", "A", "B", "c"), InsertionSort())
    sortList(listOf("1", "2", "A", "B", "c"), BubbleSort())
    sortList(listOf(1, 3, 5, -2), InsertionSort())
    sortList(listOf(1, 3, 5, -2), BubbleSort())
}