package org.ahk.kotlindesignpatterns.behavioral

abstract class ElectionsTemplate {
    private val voteCounts = mutableMapOf<String, Int>()

    private fun registerCandidate(name: String) {
        voteCounts[name] = 0
    }

    private fun voteOnCandidate(name: String) {
        voteCounts.computeIfPresent(name) { _: String, count -> count + 1 }
    }

    fun holdElection(candidates: List<String>, voterChoices: List<String>): List<String> {
        candidates.forEach(::registerCandidate)
        voterChoices.forEach(::voteOnCandidate)
        return selectElectedCandidates()
    }

    abstract fun selectElectedCandidates(): List<String>

}

class DHondtElections : ElectionsTemplate() {

    override fun selectElectedCandidates(): List<String> {
        TODO("Implement D'Hondt method for selecting election winners")
    }

}

class SaintLagueElections : ElectionsTemplate() {

    override fun selectElectedCandidates(): List<String> {
        TODO("Implement Sainte-Laguë method for selecting election winners")
    }

}

fun main(args: Array<String>) {
    val winners = DHondtElections().holdElection(
            listOf("A", "B", "C"),
            listOf("A", "B", "A", "B", "C", "A", "A", "B", "C", "A", "B", "C", "B", "C", "C"))
}