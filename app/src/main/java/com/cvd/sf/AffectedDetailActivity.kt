package com.cvd.sf

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cvd.sf.Utils.*
import kotlinx.android.synthetic.main.activity_affected_detail.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 23/03/20
 */

class AffectedDetailActivity : AppCompatActivity() {

    var countryName: String = ""
    var lastUpdate: Long = 0L
    var recovered: Int = 0
    var confirmed: Int = 0
    var deaths: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affected_detail)

        getIntentExtra()
        initListener()
        initData()
    }

    private fun getIntentExtra() {
        if (intent.hasExtra(Extra.COUNTRY)) countryName = intent.getStringExtra(Extra.COUNTRY)
        if (intent.hasExtra(Extra.RECOVERED)) recovered = intent.getIntExtra(Extra.RECOVERED, 0)
        if (intent.hasExtra(Extra.CONFIRMED)) confirmed = intent.getIntExtra(Extra.CONFIRMED, 0)
        if (intent.hasExtra(Extra.DEATHS)) deaths = intent.getIntExtra(Extra.DEATHS, 0)
        if (intent.hasExtra(Extra.LAST_UPDATE)) intent.getLongExtra(Extra.LAST_UPDATE, 0).let { lastUpdate = it }
    }

    private fun initData() {
        val total = confirmed + recovered + deaths
        tvConfirmedCasesTitle.text = "Kasus $countryName"
        tvLastUpdate.text = "Last Update: ${milisToDate(lastUpdate)}"
        tvCasesCountry.text = thousandSeparatorUtils(total)
        tvCountryConfirmed.text = thousandSeparatorUtils(confirmed)
        tvCountryRecovered.text = thousandSeparatorUtils(recovered)
        tvCountryDeaths.text = thousandSeparatorUtils(deaths)

        val pieData = ArrayList<SliceValue>()
        pieData.add(
            SliceValue(
                deaths.toFloat(),
                ContextCompat.getColor(this, R.color.colorRedDark)
            ).setLabel("")
        )
        pieData.add(
            SliceValue(
                recovered.toFloat(),
                ContextCompat.getColor(this, R.color.colorGreen)
            ).setLabel("")
        )
        pieData.add(
            SliceValue(
                confirmed.toFloat(),
                ContextCompat.getColor(this, R.color.colorYellow)
            ).setLabel("")
        )
        val pieChartData = PieChartData(pieData)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14
        pieChartData.setHasCenterCircle(true)
        chartIndoesiaCases.pieChartData = pieChartData
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
    }

    private fun intentUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }
}