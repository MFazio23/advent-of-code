package dev.mfazio.aoc.shared.extensions

import java.time.temporal.ChronoUnit

val ChronoUnit.shortText: String
    get() = when (this) {
        ChronoUnit.NANOS -> "ns"
        ChronoUnit.MICROS -> "µs"
        ChronoUnit.MILLIS -> "ms"
        ChronoUnit.SECONDS -> "s"
        ChronoUnit.MINUTES -> "m"
        ChronoUnit.HOURS -> "h"
        ChronoUnit.HALF_DAYS -> "hd"
        ChronoUnit.DAYS -> "d"
        else -> "unknown"
    }

val ChronoUnit.text: String
    get() = when (this) {
        ChronoUnit.NANOS -> "nanoseconds"
        ChronoUnit.MICROS -> "microseconds"
        ChronoUnit.MILLIS -> "milliseconds"
        ChronoUnit.SECONDS -> "seconds"
        ChronoUnit.MINUTES -> "minutes"
        ChronoUnit.HOURS -> "hours"
        ChronoUnit.HALF_DAYS -> "half days"
        ChronoUnit.DAYS -> "days"
        else -> "unknown"
    }