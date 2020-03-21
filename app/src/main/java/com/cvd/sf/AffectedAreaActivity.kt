package com.cvd.sf

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cvd.sf.Api.ApiUrl
import com.cvd.sf.Model.ConfirmedCasesModel
import com.cvd.sf.Model.CountryDetail
import com.cvd.sf.adapter.AffectedCountryAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_affected_area.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class AffectedAreaActivity : AppCompatActivity () {
    private lateinit var affectedCountryDetail: AffectedCountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affected_area)
        getData()
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(this)

        val getRequest = StringRequest(
            Request.Method.GET, ApiUrl.BASE_URL,
            Response.Listener<String> { response ->
                val gsonBuilder = GsonBuilder().create()
                val data = gsonBuilder.fromJson(response, ConfirmedCasesModel::class.java)
//                initData(data)
                Log.d("Response", response)
            },
            Response.ErrorListener {
                Log.d("Error.Response", it.toString())
            }
        )
        queue.add(getRequest)
    }

    private fun initData (data: List<ConfirmedCasesModel>) {
        affectedCountryDetail = AffectedCountryAdapter(this)
        rvAffectedCountry.adapter = affectedCountryDetail
        affectedCountryDetail.addAll(data)
    }
}