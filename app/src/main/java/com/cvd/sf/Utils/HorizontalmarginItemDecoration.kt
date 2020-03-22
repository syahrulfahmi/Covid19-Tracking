package com.cvd.sf.Utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class HorizontalMarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            parent.adapter?.let {
                val position = parent.getChildAdapterPosition(view)
                if (position == 0) {
                    right = spaceHeight / 2
                } else if (position > 0 && position < parent.adapter !!.itemCount - 1) {
                    left = spaceHeight / 2
                    right = spaceHeight / 2
                } else {
                    left = spaceHeight / 2
                }
            }
        }
    }
}