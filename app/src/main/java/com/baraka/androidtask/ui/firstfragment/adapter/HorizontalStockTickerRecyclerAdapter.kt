package com.baraka.androidtask.ui.firstfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baraka.androidtask.R
import com.baraka.androidtask.data.models.stocktickers.StocksResponseItem


class HorizontalStockTickerRecyclerAdapter(
    private val list: List<StocksResponseItem>,
    private val listener: ClickItemListener
) :
    RecyclerView.Adapter<HorizontalStockTickerRecyclerAdapter.PostsViewHolder>() {

    interface ClickItemListener {
        fun onClicked(position: Int)
        fun onProductLiked(position: Int, isLiked: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val postsResponseItem: StocksResponseItem = list[position]
        holder.bind(postsResponseItem)


//        holder.linearlayout!!.setOnClickListener { listener.onClicked(position) }

//        holder.mLikeButton!!.setOnClickListener { listener.onProductLiked(position, true) }

    }

    override fun getItemCount(): Int = list.size


    class PostsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_horizontal_ticker, parent, false)) {
        private var tvStockTitle: TextView? = null
        private var tvStockValue: TextView? = null

        init {
            tvStockTitle = itemView.findViewById(R.id.tv_stock_title)
            tvStockValue = itemView.findViewById(R.id.tv_stock_value)

        }

        fun bind(stocks: StocksResponseItem) {
            stocks.StockItem[0].let {
                 var title = it.replace("\"", "")
                tvStockTitle?.text = title
            }

            tvStockValue?.text = stocks.StockItem[1]
        }

    }
}
