package com.cvd.sf.Utils

import android.content.res.Resources

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()