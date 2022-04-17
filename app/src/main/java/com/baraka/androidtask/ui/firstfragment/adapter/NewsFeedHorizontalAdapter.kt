package com.baraka.androidtask.ui.firstfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baraka.androidtask.R
import com.baraka.androidtask.data.models.newsfeed.Article
import com.squareup.picasso.Picasso


class NewsFeedHorizontalAdapter(
    private val list: List<Article>,
    private val listener: ClickItemListener
) :
    RecyclerView.Adapter<NewsFeedHorizontalAdapter.NewsFeedViewHolder>() {

    interface ClickItemListener {
        fun onClicked(position: Int)
        fun onProductLiked(position: Int, isLiked: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsFeedViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        val newsFeedItem: Article = list[position]
        holder.bind(newsFeedItem)


//        holder.linearlayout!!.setOnClickListener { listener.onClicked(position) }

//        holder.mLikeButton!!.setOnClickListener { listener.onProductLiked(position, true) }

    }

    override fun getItemCount(): Int = list.size


    class NewsFeedViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_horizontal_news, parent, false)) {
        private var ivNewsBanner: ImageView? = null
        private var tvNewsTitle: TextView? = null
        private var tvNewsDescription: TextView? = null

        init {
            ivNewsBanner = itemView.findViewById(R.id.iv_newsBanner)
            tvNewsTitle = itemView.findViewById(R.id.tv_news_title)
            tvNewsDescription = itemView.findViewById(R.id.tv_news_description)

        }

        fun bind(article: Article) {
            article.urlToImage?.let {
                Picasso
                    .get()
                    .load(it)
                    .noFade()
                    .into(ivNewsBanner)

            }

            article.title?.let {
                if (it.isNotEmpty())
                    tvNewsTitle?.text = it
                else
                    tvNewsTitle?.text = ""
            }

            article.description?.let {
                if (it.isNotEmpty())
                    tvNewsDescription?.text = it
                else
                    tvNewsDescription?.text = ""
            }

        }

    }
}
