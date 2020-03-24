package com.cvd.sf.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cvd.sf.Api.ApiUrl
import com.cvd.sf.Model.ConfirmedCasesModel
import com.cvd.sf.Model.MainCasesModel
import com.cvd.sf.R
import com.cvd.sf.Utils.HorizontalMarginItemDecoration
import com.cvd.sf.Utils.dpToPx
import com.cvd.sf.Utils.thousandSeparatorUtils
import com.cvd.sf.adapter.TopConfirmedAdapter
import com.cvd.sf.adapter.TopDeathsAdapter
import com.cvd.sf.adapter.TopRecoveredAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_main.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import org.json.JSONArray


/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class MainFragment : Fragment() {

    private lateinit var topRecoveredAdapter: TopRecoveredAdapter
    private lateinit var topConfirmedAdapter: TopConfirmedAdapter
    private lateinit var topDeathsAdapter: TopDeathsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun iniData(data: MainCasesModel) {
        val total = data.confirmed.value + data.recovered.value + data.deaths.value
        tvCasesReported.text = thousandSeparatorUtils(total)
        tvConfirmed.text = thousandSeparatorUtils(data.confirmed.value)
        tvRecovered.text = thousandSeparatorUtils(data.recovered.value)
        tvDeaths.text = thousandSeparatorUtils(data.deaths.value)

        val pieData = ArrayList<SliceValue>()
        pieData.add(
            SliceValue(
                data.deaths.value.toFloat(),
                ContextCompat.getColor(context!!, R.color.colorRedDark)
            ).setLabel("")
        )
        pieData.add(
            SliceValue(
                data.recovered.value.toFloat(),
                ContextCompat.getColor(context!!, R.color.colorGreen)
            ).setLabel("")
        )
        pieData.add(
            SliceValue(
                data.confirmed.value.toFloat(),
                ContextCompat.getColor(context!!, R.color.colorYellow)
            ).setLabel("")
        )
        val pieChartData = PieChartData(pieData)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14
        pieChartData.setHasCenterCircle(true)
        circularProgress.pieChartData = pieChartData
    }

    private fun initTopRecoveredList(list: List<ConfirmedCasesModel>) {
        topRecoveredAdapter = TopRecoveredAdapter(context!!)
        rvTopRecovered.adapter = topRecoveredAdapter

        LinearSnapHelper().attachToRecyclerView(rvTopRecovered)
        rvTopRecovered.addItemDecoration(HorizontalMarginItemDecoration(12.dpToPx()))
        topRecoveredIndicator.attachToRecyclerView(rvTopRecovered)
        topRecoveredAdapter.addAll(list)
    }

    private fun initTopConfirmedList(list: List<ConfirmedCasesModel>) {
        topConfirmedAdapter = TopConfirmedAdapter(context!!)
        rvTopConfirmed.adapter = topConfirmedAdapter

        LinearSnapHelper().attachToRecyclerView(rvTopConfirmed)
        rvTopConfirmed.addItemDecoration(HorizontalMarginItemDecoration(12.dpToPx()))
        topConfirmedIndicator.attachToRecyclerView(rvTopConfirmed)
        topConfirmedAdapter.addAll(list)
    }

    private fun initTopDeathsList(list: List<ConfirmedCasesModel>) {
        topDeathsAdapter = TopDeathsAdapter(context!!)
        rvTopDeaths.adapter = topDeathsAdapter

        LinearSnapHelper().attachToRecyclerView(rvTopDeaths)
        rvTopDeaths.addItemDecoration(HorizontalMarginItemDecoration(12.dpToPx()))
        topDeathsIndicator.attachToRecyclerView(rvTopDeaths)
        topDeathsAdapter.addAll(list)
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(activity)

        val getConfirmedCases = StringRequest(
            Request.Method.GET, ApiUrl.BASE_URL,
            Response.Listener<String> { response ->
                val gsonBuilder = GsonBuilder().create()
                val data = gsonBuilder.fromJson(response, MainCasesModel::class.java)
                iniData(data)
            },
            Response.ErrorListener {
                Log.d("Error.Response", it.toString())
            })

        val getListTopRecovered = JsonArrayRequest(Request.Method.GET,
            ApiUrl.BASE_URL + "recovered",
            null,
            Response.Listener<JSONArray> { response ->
                Log.d("Response", response.toString())
                val gsonBuilder = GsonBuilder().create()
                val token: TypeToken<List<ConfirmedCasesModel?>?> =
                    object : TypeToken<List<ConfirmedCasesModel?>?>() {}
                val data: List<ConfirmedCasesModel> =
                    gsonBuilder.fromJson(response.toString(), token.type)
                initTopRecoveredList(data)
            },
            Response.ErrorListener {

            })

        val getListTopConfirmed = JsonArrayRequest(Request.Method.GET,
            ApiUrl.BASE_URL + "confirmed",
            null,
            Response.Listener<JSONArray> { response ->
                Log.d("Response", response.toString())
                val gsonBuilder = GsonBuilder().create()
                val token: TypeToken<List<ConfirmedCasesModel?>?> =
                    object : TypeToken<List<ConfirmedCasesModel?>?>() {}
                val data: List<ConfirmedCasesModel> =
                    gsonBuilder.fromJson(response.toString(), token.type)
                initTopConfirmedList(data)
            },
            Response.ErrorListener {

            })

        val getListTopDeaths = JsonArrayRequest(Request.Method.GET,
            ApiUrl.BASE_URL + "deaths",
            null,
            Response.Listener<JSONArray> { response ->
                Log.d("Response", response.toString())
                val gsonBuilder = GsonBuilder().create()
                val token: TypeToken<List<ConfirmedCasesModel?>?> =
                    object : TypeToken<List<ConfirmedCasesModel?>?>() {}
                val data: List<ConfirmedCasesModel> =
                    gsonBuilder.fromJson(response.toString(), token.type)
                initTopDeathsList(data)
            },
            Response.ErrorListener {

            })
        queue.add(getConfirmedCases)
        queue.add(getListTopRecovered)
        queue.add(getListTopConfirmed)
        queue.add(getListTopDeaths)
    }

}