package dev.mfazio.aoc.twentythree.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-five.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Long {

    val seeds = input
        .first()
        .replace("seeds: ", "")
        .split(" ")
        .map { it.toLong() }
        .chunked(2)

    val fullMap = getFullMapFromInput(
        input.drop(1)
    )

    val ranges = fullMap.mapRanges(
        seeds.map { (start, size) -> start to (start + size - 1) }
    )

    return ranges.minOf { (start, _) -> start }
}

fun FullMap.mapRanges(ranges: List<Pair<Long, Long>>): List<Pair<Long, Long>> = listOf(
    this.seedToSoil,
    this.soilToFertilizer,
    this.fertilizerToWater,
    this.waterToLight,
    this.lightToTemperature,
    this.temperatureToHumidity,
    this.humidityToLocation
).fold(ranges) { currentRanges, fullMaps  ->
    currentRanges.map { (start, end) ->
        fullMaps.map { fullMap ->
            fullMap.mapRangeToItem(start, end)
        }.flatten().distinct()
    }.flatten().map { (start, end) ->
        val mappings = fullMaps.mapNotNull { fullMap ->
            fullMap.mapSourceToDestination(start, end)
        }.distinct()
        mappings.ifEmpty { listOf(start to end) }
    }.flatten()
}

fun MapItem.mapRangeToItem(start: Long, end: Long): List<Pair<Long, Long>> = listOfNotNull(
    if (start < sourceStart) start to minOf(end, sourceStart - 1) else null,
    if (end > sourceEnd) maxOf(start, sourceEnd + 1) to end else null,
    if (start <= sourceEnd && end >= sourceStart) maxOf(start, sourceStart) to minOf(end, sourceEnd) else null
)

fun MapItem.mapSourceToDestination(start: Long, end: Long): Pair<Long, Long>? =
    if (start <= sourceEnd && end >= sourceStart) {
        val offset = destinationStart - sourceStart
        val newStart = maxOf(start, sourceStart) + offset
        val newEnd = minOf(end, sourceEnd) + offset

        newStart to newEnd
    } else null