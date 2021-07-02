package com.cvd.sf

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.cvd.sf.api.ApiUrl
import com.cvd.sf.Model.ProvinceModel
import com.cvd.sf.adapter.ProvinceMainListAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_affected_area.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class AffectedAreaActivity : AppCompatActivity() {

    private lateinit var provincListAdapter: ProvinceMainListAdapter
    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affected_area)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        getData()
        initScrolling()
        initListener()
    }

    private fun initScrolling() {
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                btnUp.hide()
            }
            if (scrollY < oldScrollY) {
                btnUp.show()
            }
        })
    }

    private fun initListener() {
        btnUp.setOnClickListener {
            nestedScrollView.scrollTo(0, 0)
        }
    }

    private fun initData(data: List<ProvinceModel>) {
        provincListAdapter = ProvinceMainListAdapter(this)
        rvAffectedCountry.adapter = provincListAdapter
        provincListAdapter.addAll(data)
    }

    private fun getData() {
        queue = Volley.newRequestQueue(this)

        val getListProvince = JsonArrayRequest(Request.Method.GET,
            ApiUrl.API_PROVINCE,
            null,
            { response ->
                Log.d("Response", response.toString())
                val gsonBuilder = GsonBuilder().create()
                val token: TypeToken<List<ProvinceModel?>?> =
                    object : TypeToken<List<ProvinceModel?>?>() {}
                val data: List<ProvinceModel> =
                    gsonBuilder.fromJson(response.toString(), token.type)
                initData(data)
            },
            {

            })
        queue.add(getListProvince)
    }
}