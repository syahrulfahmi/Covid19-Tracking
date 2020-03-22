package com.cvd.sf.Utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */
fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}
fun milisToDate(milis: Long): String {
    val simpleDateFormat = SimpleDateFormat("dd MMM yyyy")
    val dateString = simpleDateFormat.format(milis)
    return String.format("%s", dateString)
}