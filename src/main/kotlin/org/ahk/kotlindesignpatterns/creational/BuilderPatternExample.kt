package org.ahk.kotlindesignpatterns.creational

import kotlinx.collections.immutable.toImmutableList

enum class ProgrammingLanguage {
    Java,
    Cpp,
    C,
    Kotlin,
    Haskell,
    Scala
}

interface SoftSkill

enum class ForeignLanguage : SoftSkill {
    English,
    German,
    French
}

enum class PersonalTraits : SoftSkill {
    Organized,
    Helpful,
    OpenMinded
}

enum class CommunicationType {
    NERD,
    VERY_POOR,
    POOR,
    FAIR,
    SHINING_CONFERENCE_STAR
}

class SoftwareDeveloper private constructor(builder: Builder) {
    val firstName: String
    val lastName: String
    val communicationType: CommunicationType
    val programmingLanguages: List<ProgrammingLanguage>
    val softSkills: List<SoftSkill>

    init {
        firstName = builder.firstName
        lastName = builder.lastName
        communicationType = builder.communicationType
        programmingLanguages = builder.programmingLanguages.toImmutableList()
        softSkills = builder.softSkills.toImmutableList()
    }

    class Builder(val firstName: String, val lastName: String, val communicationType: CommunicationType) {
        var programmingLanguages: MutableList<ProgrammingLanguage> = mutableListOf()

        var softSkills: MutableList<SoftSkill> = mutableListOf()

        fun addProgrammingLanguages(programmingLanguage: ProgrammingLanguage): Builder {
            programmingLanguages.add(programmingLanguage)
            return this
        }

        fun addSoftSkill(softSkill: SoftSkill): Builder {
            softSkills.add(softSkill)
            return this
        }

        fun build() = SoftwareDeveloper(this)
    }

    override fun toString(): String {
        return "SoftwareDeveloper(firstName='$firstName', lastName='$lastName', communicationType=$communicationType, programmingLanguages=$programmingLanguages, softSkills=$softSkills)"
    }

}


fun main(args: Array<String>) {
    val richardGreat = SoftwareDeveloper.Builder("Richard", "Great", CommunicationType.NERD)
            .addSoftSkill(ForeignLanguage.English)
            .addSoftSkill(ForeignLanguage.German)
            .addProgrammingLanguages(ProgrammingLanguage.C)
            .addProgrammingLanguages(ProgrammingLanguage.Cpp)
            .addProgrammingLanguages(ProgrammingLanguage.Haskell)
            .build()
    println(richardGreat)
}