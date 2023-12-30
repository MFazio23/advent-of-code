package dev.mfazio.aoc.twentythree.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.isNotNullOrBlank

fun main() {
    println(
        partOne(
            getResourceAsListOfStrings("day-five.txt")
        )
    )
}

fun partOne(input: List<String>): Long {

    val seeds = input.first().replace("seeds: ", "").split(" ").map { it.toLong() }
    val fullMap = getFullMapFromInput(input.drop(1))

    return seeds.minOfOrNull { fullMap.mapSeed(it) } ?: -1L

}

fun getFullMapFromInput(input: List<String>): FullMap {
    val fullMap = FullMap()
    var category: String? = null
    val mapItems = mutableListOf<MapItem>()

    input.forEach { line ->
        if (line.contains(":")) {
            category = line.replace(" map:", "")
        } else if (line.isNotNullOrBlank()) {
            line.split(" ").let {
                mapItems.add(
                    MapItem(
                        destinationStart = it[0].toLong(),
                        sourceStart = it[1].toLong(),
                        length = it[2].toLong()
                    )
                )
            }
        } else if (category != null && mapItems.any()) {
            when (category) {
                "seed-to-soil" -> fullMap.seedToSoil = mapItems.toList()
                "soil-to-fertilizer" -> fullMap.soilToFertilizer = mapItems.toList()
                "fertilizer-to-water" -> fullMap.fertilizerToWater = mapItems.toList()
                "water-to-light" -> fullMap.waterToLight = mapItems.toList()
                "light-to-temperature" -> fullMap.lightToTemperature = mapItems.toList()
                "temperature-to-humidity" -> fullMap.temperatureToHumidity = mapItems.toList()
                "humidity-to-location" -> fullMap.humidityToLocation = mapItems.toList()
            }
            category = null
            mapItems.clear()
        }
    }

    return fullMap
}

data class FullMap(
    var seedToSoil: List<MapItem> = emptyList(),
    var soilToFertilizer: List<MapItem> = emptyList(),
    var fertilizerToWater: List<MapItem> = emptyList(),
    var waterToLight: List<MapItem> = emptyList(),
    var lightToTemperature: List<MapItem> = emptyList(),
    var temperatureToHumidity: List<MapItem> = emptyList(),
    var humidityToLocation: List<MapItem> = emptyList(),
) {
    private fun Long.mapValue(map: List<MapItem>): Long =
        map.firstOrNull { (it.sourceStart..(it.sourceStart + it.length)).contains(this) }?.let { range ->
            val offset = this - range.sourceStart
            range.destinationStart + offset
        } ?: this

    fun mapSeed(seed: Long) =
        seed
            .mapValue(seedToSoil)
            .mapValue(soilToFertilizer)
            .mapValue(fertilizerToWater)
            .mapValue(waterToLight)
            .mapValue(lightToTemperature)
            .mapValue(temperatureToHumidity)
            .mapValue(humidityToLocation)

}

data class MapItem(
    val destinationStart: Long,
    val sourceStart: Long,
    val length: Long,
) {
    val sourceEnd = sourceStart + length - 1
}