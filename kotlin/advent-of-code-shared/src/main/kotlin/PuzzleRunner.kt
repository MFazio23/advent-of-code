package dev.mfazio.aoc.shared

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.system.measureTimeMillis

suspend fun <T> runPuzzle(
    inputFileName: String,
    hidePeriodicMessages: Boolean = false,
    periodicMessageIntervalMs: Long = 30_000, //30 seconds
    puzzle: (input: List<String>) -> T,
) = coroutineScope {
    val job = launch {
        val startTime = LocalTime.now()
        while (true) {
            val elapsedTime =
                ChronoUnit.MILLIS.between(startTime, LocalTime.now())
            if (!hidePeriodicMessages && elapsedTime > periodicMessageIntervalMs) {
                println("Running for ${getTimeTextFromMillis(elapsedTime, periodicMessageIntervalMs)}...")
            }
            delay(periodicMessageIntervalMs)
        }
    }
    measureTimeMillis {
        val result = puzzle(
            getResourceAsListOfStrings(inputFileName)
        )
        println()
        println("Result: $result")
    }.also {
        job.cancel()
        println("Time taken: ${getTimeTextFromMillis(it, periodicMessageIntervalMs)} (${it}ms)")
    }
}

fun getTimeTextFromMillis(millis: Long, interval: Long): String = when {
    millis < 1_000 -> "${millis}ms"
    millis < 60_000 -> {
        val msText = if (interval < 1000) {
            ", ${millis % 1_000}ms"
        } else ""
        "${millis / 1_000}s$msText"
    }
    else -> {
        val seconds = (millis % 60_000) / 1_000
        val secondsText = if (seconds < 10) "0$seconds" else "$seconds"
        "${millis / 60_000}m, ${secondsText}s"
    }
}