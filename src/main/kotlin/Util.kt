package dev.mfazio.aoc

object Util {
    fun getInput(fileName: String) = this::class.java.getResource(fileName).readText()
}