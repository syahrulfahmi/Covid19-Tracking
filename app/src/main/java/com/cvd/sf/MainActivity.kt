package com.cvd.sf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cvd.sf.fragment.MainFragment
import com.cvd.sf.adapter.TabAdapter
import com.cvd.sf.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var adapter: TabAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TabAdapter(supportFragmentManager)
        adapter!!.addFragment(MainFragment(), "Ringkasan")
        adapter!!.addFragment(ListFragment(), "Kasus Indonesia")
        adapter!!.addFragment(MainFragment(), "Kasus Keseluruhan")

        viewPager.adapter = adapter
        viewPager.setSwipeEanble(false)
        tabLayout.setupWithViewPager(viewPager)
    }

}
