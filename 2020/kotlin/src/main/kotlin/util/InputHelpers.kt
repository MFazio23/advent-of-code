package dev.mfazio.aoc.twenty.util

object InputHelpers {

    fun getContentsFromFile(path: String): String? = javaClass.getResource(path)?.readText()

    fun getListOfStringsFromFile(path: String): List<String> = getContentsFromFile(path)?.let { contents ->
        contents.split("\n")
    } ?: emptyList()

}

fun main() {
    val inputs = InputHelpers.getListOfStringsFromFile("/dayone.txt")
    println(inputs)
}