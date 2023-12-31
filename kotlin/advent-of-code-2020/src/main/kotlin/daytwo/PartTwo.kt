package dev.mfazio.aoc.twenty.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun passwordPhilosophyPositional(passwordEntries: List<String>) = passwordEntries.count { checkPassword(it) }

private fun checkPassword(passwordEntryString: String): Boolean {
    val entry = PasswordPositionalEntry.fromString(passwordEntryString)

    return entry?.isPasswordValid() == true
}

data class PasswordPositionalEntry(
    val password: String,
    val requiredLetter: String,
    val firstPosition: Int,
    val secondPosition: Int,
) {

    fun isPasswordValid() =
        listOf(password[firstPosition - 1], password[secondPosition - 1]).count { it.toString() == requiredLetter } == 1

    companion object {
        private val passwordEntryRegex = "(\\d+)-(\\d+) (\\S): (\\S+)".toRegex()
        fun fromString(input: String): PasswordPositionalEntry? {
            val regexResult = passwordEntryRegex.find(input)?.groupValues ?: emptyList()

            if (regexResult.size != 5) return null

            return PasswordPositionalEntry(
                regexResult[4],
                regexResult[3],
                regexResult[1].toInt(),
                regexResult[2].toInt(),
            )
        }
    }
}

fun main() {
    val inputs = getResourceAsListOfStrings("daytwo.txt")

    println("Result = ${passwordPhilosophyPositional(inputs)}")
}