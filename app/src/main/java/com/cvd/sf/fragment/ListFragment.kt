package com.cvd.sf.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.cvd.sf.Api.ApiUrl
import com.cvd.sf.Model.ConfirmedCasesModel
import com.cvd.sf.R
import com.cvd.sf.adapter.AffectedCountryAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_affected_area.*
import org.json.JSONArray

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class ListFragment : Fragment() {

    private lateinit var affectedCountryDetail: AffectedCountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_affected_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(activity)
        val url = ApiUrl.BASE_URL + ApiUrl.CONFIRMED_CASES_URL

        val getRequest = JsonArrayRequest(Request.Method.GET,
            url,
            null,
            Response.Listener<JSONArray> { response ->
                Log.d("Response", response.toString())
                val gsonBuilder = GsonBuilder().create()
                val token: TypeToken<List<ConfirmedCasesModel?>?> =
                    object : TypeToken<List<ConfirmedCasesModel?>?>() {}
                val data: List<ConfirmedCasesModel> =
                    gsonBuilder.fromJson(response.toString(), token.type)
                initData(data)
            },
            Response.ErrorListener {

            })
        queue.add(getRequest)
    }

    private fun initData(data: List<ConfirmedCasesModel>) {
        affectedCountryDetail = AffectedCountryAdapter()
        rvAffectedCountry.adapter = affectedCountryDetail
        affectedCountryDetail.addAll(data)
    }
}