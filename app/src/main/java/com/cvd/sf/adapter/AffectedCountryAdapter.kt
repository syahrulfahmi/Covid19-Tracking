package com.cvd.sf.adapter

import android.content.Context
import android.content.Intent
import com.cvd.sf.Model.ConfirmedCasesModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cvd.sf.AffectedAreaActivity
import com.cvd.sf.AffectedDetailActivity
import com.cvd.sf.R
import com.cvd.sf.Utils.Extra
import com.cvd.sf.Utils.milisToDate
import com.cvd.sf.Utils.thousandSeparatorUtils
import kotlinx.android.synthetic.main.item_affected_country.view.*
import java.util.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class AffectedCountryAdapter(private val context: Context) :
    RecyclerView.Adapter<AffectedCountryAdapter.ItemViewHolder>() {

    private var items = ArrayList<ConfirmedCasesModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_affected_country, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        itemViewHolder.bind(position)
    }

    override fun getItemCount(): Int {
        return 100
    }

    fun addAll(data: List<ConfirmedCasesModel>) {
        items = ArrayList(data)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var item: ConfirmedCasesModel

        fun bind(position: Int) {
            item = items[position]

            itemView.tvCountry.text = item.countryRegion
            itemView.tvRecoveredCount.text = thousandSeparatorUtils(item.recovered)
            itemView.tvPositiveCount.text = thousandSeparatorUtils(item.confirmed)
            itemView.tvDeathsCount.text = thousandSeparatorUtils(item.deaths)
            itemView.tvLastUpdateItem.text = "Last Update: ${milisToDate(item.lastUpdate)}"
            val activity = (context as AffectedAreaActivity)
            itemView.cvListAffected.setOnClickListener {
                val intent = Intent(activity, AffectedDetailActivity::class.java).apply {
                    putExtra(Extra.COUNTRY, item.countryRegion)
                    putExtra(Extra.RECOVERED, item.recovered)
                    putExtra(Extra.CONFIRMED, item.confirmed)
                    putExtra(Extra.DEATHS, item.deaths)
                    putExtra(Extra.LAST_UPDATE, item.lastUpdate)
                }
                activity.startActivity(intent)
            }
        }
    }
}
