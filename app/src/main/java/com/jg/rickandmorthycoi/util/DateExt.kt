package com.jg.rickandmorthycoi.util

import java.util.Calendar


fun String.yearsAgo(): Int {
    val year = this.substringBefore("-").toIntOrNull() ?: return 0
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    return currentYear - year
}