package com.cvd.sf.adapter

import com.cvd.sf.Model.ConfirmedCasesModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cvd.sf.R
import kotlinx.android.synthetic.main.item_affected_country.view.*
import java.util.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class AffectedCountryAdapter(var context: Context) :
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
        return items.size
    }

    fun addAll(data: List<ConfirmedCasesModel>) {
        items = ArrayList(data)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var item: ConfirmedCasesModel

        fun bind(position: Int) {
            item = items[position]

            itemView.country.text = item.countryRegion

        }
    }
}
