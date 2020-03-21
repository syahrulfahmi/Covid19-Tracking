package com.cvd.sf

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cvd.sf.Api.ApiUrl
import com.cvd.sf.Model.MainCasesModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh.setOnRefreshListener {
            getData()
            swipeRefresh.isRefreshing = false
        }
        btnMove.setOnClickListener {
            val intent = Intent(this, AffectedAreaActivity::class.java)
            startActivity(intent)
        }

        getData()
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(this)

        val getRequest = StringRequest(Request.Method.GET, ApiUrl.BASE_URL,
                Response.Listener<String> { response ->
                    val gsonBuilder = GsonBuilder().create()
                    val data = gsonBuilder.fromJson(response, MainCasesModel::class.java)
                    initObserver(data)
                    Log.d("Response", response)
                },
                Response.ErrorListener {
                    Log.d("Error.Response", it.toString())
                }
        )
        queue.add(getRequest)
    }

    private fun initObserver(data: MainCasesModel) {
        val total = data.confirmed.value + data.recovered.value + data.deaths.value
        textViewJson.text = "Data Meninggal: ${data.deaths.value}"
        textPercentage.text = ((data.deaths.value / total) * 100).toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            circularProgress.setProgress( 80/**((data.deaths.value / total) * 100)*/, true)
        }
    }
}
