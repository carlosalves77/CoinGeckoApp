package com.carlos.coingecko.presentation.CoinList

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.carlos.coingecko.MainActivity
import com.carlos.coingecko.databinding.CoinRecyclerViewBinding
import com.carlos.coingecko.domain.model.Coin
import com.squareup.picasso.Picasso

class CoinAdapter(private val context : Context, var coinList : ArrayList<Coin>)
    : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(), Filterable
{

    lateinit var filteredList: ArrayList<Coin>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinAdapter.CoinViewHolder {
         return CoinViewHolder(CoinRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CoinAdapter.CoinViewHolder, position: Int) {
      holder.binding.apply {
          txtCoinName.text = coinList[position].name
          Picasso.get().load(coinList[position].image).into(imgCoinImage)
          coinLinearLayout.setOnClickListener {
              val intent = Intent(context, MainActivity::class.java)
              context.startActivity(intent)
              intent.let { content ->
                  content.putExtra("id", coinList[position].id)
                  content.putExtra("name",coinList[position].name)
                  content.putExtra("image",coinList[position].image)
                  content.putExtra("market_cap",coinList[position].market_cap)
                  content.putExtra("price",coinList[position].price)
                  content.putExtra("price_percent_change",coinList[position].price_percent_change)
                  content.putExtra("lowPrice",coinList[position].lowPrice)
                  content.putExtra("highPrice",coinList[position].highPrice)

              }
          }
      }
    }
    override fun getItemCount() = coinList.size

    inner class CoinViewHolder(val binding : CoinRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list : ArrayList<Coin>) {
        this.filteredList = list
        this.coinList = list
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object  : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val string = constraint?.toString() ?: ""
                if (string.isEmpty()) {
                    val arrayList = arrayListOf<Coin>()
                    filteredList.filter {
                        it.name.lowercase().contains(string.lowercase())
                    }.forEach {
                        arrayList.add(it)
                    }
                    filteredList.clear()
                    filteredList.addAll(arrayList)
                } else {
                    filteredList = coinList
                }
                return FilterResults().apply {
                    this.values = filteredList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               if (results?.values == null) {
                   ArrayList<Coin>()
               } else {
                   setData(filteredList)
               }
            }

        }
    }
}