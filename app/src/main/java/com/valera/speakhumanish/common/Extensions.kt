package com.valera.speakhumanish.common

fun String.splitNumbers(delimiter : String) : Set<Long> {
    if (this == "") {
        return HashSet()
    }
    return this.split(delimiter).map { t -> t.toLong() }.toSet()
}