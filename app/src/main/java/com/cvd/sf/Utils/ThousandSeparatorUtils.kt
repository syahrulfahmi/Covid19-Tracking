package com.cvd.sf.Utils

import java.text.DecimalFormat

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

fun thousandSeparatorUtils(number: Int): String {
    val decimalFormat = DecimalFormat("#,###.##")
    return decimalFormat.format(number)
}