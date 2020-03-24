package com.cvd.sf.fragment

import android.content.Intent
import android.net.Uri
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
import com.cvd.sf.AffectedAreaActivity
import com.cvd.sf.Api.ApiUrl
import com.cvd.sf.Model.CountryCasesModel
import com.cvd.sf.Model.ProvinceModel
import com.cvd.sf.R
import com.cvd.sf.Utils.*
import com.cvd.sf.adapter.ProvinceListAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_indonesia_cases.*
import kotlinx.android.synthetic.main.layout_whats_do.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import org.json.JSONArray


/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class IndonesiaCasesFragment : Fragment() {

    private lateinit var provinceListAdapter: ProvinceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_indonesia_cases, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        initListener()
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(activity)

        val getConfirmedCases = StringRequest(
            Request.Method.GET, ApiUrl.BASE_URL + "countries/indonesia",
            Response.Listener<String> { response ->
                val gsonBuilder = GsonBuilder().create()
                val data = gsonBuilder.fromJson(response, CountryCasesModel::class.java)
                initData(data)
            },
            Response.ErrorListener {
                Log.d("Error.Response", it.toString())
            })

        val getListProvince = JsonArrayRequest(Request.Method.GET,
            ApiUrl.API_PROVINCE,
            null,
            Response.Listener<JSONArray> { response ->
                Log.d("Response", response.toString())
                val gsonBuilder = GsonBuilder().create()
                val token: TypeToken<List<ProvinceModel?>?> =
                    object : TypeToken<List<ProvinceModel?>?>() {}
                val data: List<ProvinceModel> =
                    gsonBuilder.fromJson(response.toString(), token.type)
                initRecylerView(data)
            },
            Response.ErrorListener {

            })

        queue.add(getListProvince)
        queue.add(getConfirmedCases)
    }

    private fun initData(data: CountryCasesModel) {
        val total = data.confirmed.value + data.recovered.value + data.deaths.value
        val date = data.lastUpdate.toDate()?.formatTo("dd MMM yyyy")
        tvLastUpdate.text = "Last Update: $date"
        tvCasesCountry.text = thousandSeparatorUtils(total)
        tvCountryConfirmed.text = thousandSeparatorUtils(data.confirmed.value)
        tvCountryRecovered.text = thousandSeparatorUtils(data.recovered.value)
        tvCountryDeaths.text = thousandSeparatorUtils(data.deaths.value)

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
        chartIndoesiaCases.pieChartData = pieChartData
    }

    private fun initRecylerView(list: List<ProvinceModel>) {
        provinceListAdapter = ProvinceListAdapter(context!!)
        rvProvince.adapter = provinceListAdapter

        LinearSnapHelper().attachToRecyclerView(rvProvince)
        rvProvince.addItemDecoration(HorizontalMarginItemDecoration(12.dpToPx()))
        provinceIndicator.attachToRecyclerView(rvProvince)
        provinceListAdapter.addAll(list)
    }

    private fun initListener() {
        cvWhatDo.setOnClickListener {
            intentUrl("https://www.alodokter.com/ketahui-cara-untuk-mencegah-penularan-virus-corona")
        }
        cvWhatDo2.setOnClickListener {
            intentUrl("https://www.alodokter.com/tampak-mirip-ketahui-beda-gejala-virus-corona-dengan-flu-biasa")
        }
        cvWhatDo3.setOnClickListener {
            intentUrl("https://www.tribunnews.com/corona/2020/03/22/ciri-ciri-virus-corona-bentuk-hingga-lamanya-pengembangan-vaksin-covid-19")
        }
        tvSeeMore.setOnClickListener {
            val intent = Intent(activity, AffectedAreaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun intentUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }
}