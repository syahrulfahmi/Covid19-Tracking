package com.cvd.sf.adapter

import android.content.Context
import com.cvd.sf.Model.ConfirmedCasesModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cvd.sf.R
import com.cvd.sf.Utils.thousandSeparatorUtils
import kotlinx.android.synthetic.main.item_top_affected.view.*
import java.util.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class TopDeathsAdapter (private val context: Context) :
    RecyclerView.Adapter<TopDeathsAdapter.ItemViewHolder>() {

    private var items = ArrayList<ConfirmedCasesModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_top_affected, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        itemViewHolder.bind(position)
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun addAll(data: List<ConfirmedCasesModel>) {
        items = ArrayList(data)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var item: ConfirmedCasesModel

        fun bind(position: Int) {
            item = items[position]
            itemView.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorRedDark))
            itemView.tvRecoveredTotal.setTextColor(ContextCompat.getColor(context, R.color.colorRedDark))
            itemView.tvTitle.text = "Total Meninggal"

            itemView.tvCountry.text = item.countryRegion
            itemView.tvProvince.text = item.provinceState
            if (item.provinceState != null) {
                itemView.tvProvince.visibility = View.VISIBLE
            } else {
                itemView.tvProvince.visibility = View.GONE
            }
            itemView.tvRecoveredTotal.text = thousandSeparatorUtils(item.deaths)
        }
    }
}
