package dev.mfazio.aoc.twentytwo.util

object InputHelpers {
    fun getContentsFromFile(path: String): String? = javaClass.getResource(path)?.readText()

    fun getListOfStringsFromFile(path: String): List<String> =
        getContentsFromFile(path)?.split("\n") ?: emptyList()
}