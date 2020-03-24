package com.cvd.sf.adapter

import android.content.Context
import com.cvd.sf.Model.ProvinceModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cvd.sf.R
import com.cvd.sf.Utils.thousandSeparatorUtils
import kotlinx.android.synthetic.main.item_province_list.view.*
import java.util.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class ProvinceMainListAdapter(private val context: Context) :
    RecyclerView.Adapter<ProvinceMainListAdapter.ItemViewHolder>() {

    private var items = ArrayList<ProvinceModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_province_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        itemViewHolder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addAll(data: List<ProvinceModel>) {
        items = ArrayList(data)
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var item: ProvinceModel

        fun bind(position: Int) {
            item = items[position]

            itemView.tvCountry.text = item.attributes.provinsi
            itemView.tvRecoveredCount.text = thousandSeparatorUtils(item.attributes.kasusSemb)
            itemView.tvPositiveCount.text = thousandSeparatorUtils(item.attributes.kasusPosi)
            itemView.tvDeathsCount.text = thousandSeparatorUtils(item.attributes.kasusMeni)
        }
    }
}
