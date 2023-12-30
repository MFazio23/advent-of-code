package dev.mfazio.aoc.twenty.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun passwordPhilosophy(passwordEntries: List<String>) = passwordEntries.count { checkPassword(it) }

private fun checkPassword(passwordEntryString: String): Boolean {
    val entry = PasswordEntry.fromString(passwordEntryString)

    return entry?.isPasswordValid() == true
}

private data class PasswordEntry(
    val password: String,
    val requiredLetter: String,
    val lowCount: Int,
    val highCount: Int,
) {

    fun isPasswordValid(): Boolean = (lowCount..highCount).contains(
        password.count { it.toString() == requiredLetter }
    )

    companion object {
        private val passwordEntryRegex = "(\\d+)-(\\d+) (\\S): (\\S+)".toRegex()
        fun fromString(input: String): PasswordEntry? {
            val regexResult = passwordEntryRegex.find(input)?.groupValues ?: emptyList()

            if (regexResult.size != 5) return null

            return PasswordEntry(
                regexResult[4],
                regexResult[3],
                regexResult[1].toInt(),
                regexResult[2].toInt(),
            )
        }
    }
}

fun main() {
    val inputs = getResourceAsListOfStrings("/daytwo.txt")

    println("Result = ${passwordPhilosophy(inputs)}")
}