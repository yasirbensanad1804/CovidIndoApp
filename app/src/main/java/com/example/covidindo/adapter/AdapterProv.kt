package com.example.covidindo.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidindo.R
import com.example.covidindo.model.DataItem
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_prov.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterProv(private val prov:ArrayList<DataItem> ,private val clickListener:(DataItem)-> Unit):
        RecyclerView.Adapter<ProvViewHolder>() ,Filterable{

    var provinsiItem =ArrayList<DataItem>()
    init {
        provinsiItem =prov
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prov,parent,false)
        return ProvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return provinsiItem.size
    }

    override fun onBindViewHolder(holder: ProvViewHolder, position: Int) {
        holder.bind(provinsiItem[position] ,clickListener)
    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constrain: CharSequence?): FilterResults {
                val charSearch =constrain.toString()
                provinsiItem =if (charSearch.isEmpty()){
                    prov
                }else{
                    val resultList =ArrayList<DataItem>()
                    for (row in prov){
                        val search = row.provinsi?.toLowerCase(Locale.ROOT) ?:""


                        if (search.contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList

                }
                val filterResult =FilterResults()
                filterResult.values =provinsiItem
                return filterResult

            }

            override fun publishResults(p0: CharSequence?, result: FilterResults?) {
                provinsiItem =result?.values as ArrayList<DataItem>
                notifyDataSetChanged()

            }

        }

    }
}


class ProvViewHolder (item: View):RecyclerView.ViewHolder(item) {
    fun bind(provinsi :DataItem ,clickListener: (DataItem) -> Unit) {
        val country_corona: TextView = itemView.negara
        val total_cases: TextView = itemView.Confirmed
        val total_recovered: TextView = itemView.Recovered
        val total_death: TextView = itemView.Deaths
        val formatter: NumberFormat = DecimalFormat("#,###")


        country_corona.negara.text = provinsi.provinsi
        total_cases.Confirmed.text = formatter.format(provinsi.kasusPosi?.toDouble())
        total_recovered.Recovered.text = formatter.format(provinsi.kasusSemb?.toDouble())
        total_death.Deaths.text = formatter.format(provinsi.kasusMeni?.toDouble())

    }
    }


