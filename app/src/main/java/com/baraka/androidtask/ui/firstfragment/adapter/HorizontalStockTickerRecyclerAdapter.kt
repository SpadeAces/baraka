package com.baraka.androidtask.ui.firstfragment.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.baraka.androidtask.R
import com.baraka.androidtask.data.models.stocktickers.Stocks
import com.baraka.androidtask.data.models.stocktickers.StocksResponseItem
import com.baraka.androidtask.utils.roundOfStocksValue


class HorizontalStockTickerRecyclerAdapter(
    private val list: List<Stocks>,
    private val context : Context,
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
        val stocks: Stocks = list[position]
        holder.bind(stocks,context)


//        holder.linearlayout!!.setOnClickListener { listener.onClicked(position) }

//        holder.mLikeButton!!.setOnClickListener { listener.onProductLiked(position, true) }

    }

    override fun getItemCount(): Int = list.size


    class PostsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_horizontal_ticker, parent, false)) {
        private var tvStockTitle: TextView? = null
        private var tvStockValue: TextView? = null
        private var clStockLayout : ConstraintLayout? = null
        private var ivStockIndicator : ImageView? = null

        init {
            tvStockTitle = itemView.findViewById(R.id.tv_stock_title)
            tvStockValue = itemView.findViewById(R.id.tv_stock_value)
            clStockLayout = itemView.findViewById(R.id.constraint_stock_layout)
            ivStockIndicator = itemView.findViewById(R.id.iv_stock_indicator)
        }

        fun bind(stocks: Stocks, context: Context) {
            var previousValue = 0.00
            stocks.title.let {
                if (it.isNotEmpty()) {
                    var title = it.replace("\"", "")
                    tvStockTitle?.text = title
                } else
                    tvStockTitle?.text = ""
            }

            stocks.value.let {
                if (!it.equals(null)) {
                    tvStockValue?.text = roundOfStocksValue(it).toString()
                } else
                    tvStockValue?.text = ""
            }

            stocks.isUp.let { isUp ->
                if (isUp){
                    clStockLayout?.background = ContextCompat.getDrawable(context, R.drawable.price_change_up_bg)
                    ivStockIndicator?.background = ContextCompat.getDrawable(context,R.drawable.up)
                }else {
                    clStockLayout?.background = ContextCompat.getDrawable(context, R.drawable.price_change_down_bg)
                    ivStockIndicator?.background = ContextCompat.getDrawable(context,R.drawable.down)
                }
            }
        }

    }
}
