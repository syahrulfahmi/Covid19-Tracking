package com.cvd.sf.Utils

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class SFViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var pageSwipeEnabled: Boolean = true

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.pageSwipeEnabled) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (this.pageSwipeEnabled) {
            super.onInterceptTouchEvent(event)
        } else false
    }

    fun setSwipeEanble(pageSwipeEnabled:Boolean) {
        this.pageSwipeEnabled = pageSwipeEnabled
    }

}