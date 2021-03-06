package com.baraka.androidtask.ui.firstfragment

import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.baraka.androidtask.BR
import com.baraka.androidtask.R
import com.baraka.androidtask.baseclasses.BaseFragment
import com.baraka.androidtask.data.local.datastore.DataStoreProvider
import com.baraka.androidtask.data.models.newsfeed.Article
import com.baraka.androidtask.data.models.stocktickers.Stocks
import com.baraka.androidtask.data.models.stocktickers.StocksResponseItem
import com.baraka.androidtask.data.remote.Resource
import com.baraka.androidtask.databinding.FirstFragmentBinding
import com.baraka.androidtask.ui.firstfragment.adapter.HorizontalStockTickerRecyclerAdapter
import com.baraka.androidtask.ui.firstfragment.adapter.NewsAdapter
import com.baraka.androidtask.ui.firstfragment.adapter.NewsFeedHorizontalAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.first_fragment.*
import java.util.*

@AndroidEntryPoint
class FirstFragment : BaseFragment<FirstFragmentBinding, FirstViewModel>() {

    override val layoutId: Int
        get() = R.layout.first_fragment
    override val viewModel: Class<FirstViewModel>
        get() = FirstViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var stockTickerAdapter: HorizontalStockTickerRecyclerAdapter
    private var stocksList: ArrayList<Stocks> = ArrayList()

    private lateinit var newsFeedHorizontalAdaper : NewsFeedHorizontalAdapter
    private lateinit var newsAdapter: NewsAdapter

    private var newsFeedList: ArrayList<Article> = ArrayList()

    private var horizontalNewsFeedList : ArrayList<Article> = ArrayList()
    lateinit var dataStoreProvider: DataStoreProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get reference to our Data Store Provider class
        dataStoreProvider = DataStoreProvider(requireContext())

        subscribeToObserveDataStore()

        //calling api
        mViewModel.getStocksFromURL()
        mViewModel.getNewsFromURL()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialising()
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()

    }

    private fun subscribeToObserveDataStore() {

    }

    private fun initialising() {
        stockTickerAdapter = HorizontalStockTickerRecyclerAdapter(stocksList, requireContext(), object : HorizontalStockTickerRecyclerAdapter.ClickItemListener {
            override fun onClicked(position: Int) {
//                Navigation.findNavController(recycler_posts)
//                    .navigate(R.id.action_firstFragment_to_secondFragment)
            }

            override fun onProductLiked(position: Int, isLiked: Boolean) {
            }

        })

        val layoutManager: LinearLayoutManager = object : LinearLayoutManager(context) {
            override fun smoothScrollToPosition(
                recyclerView: RecyclerView,
                state: RecyclerView.State,
                position: Int
            ) {
                try {
                    val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(
                        Objects.requireNonNull(
                            context
                        )
                    ) {
                        private val SPEED = 2500f // Change this value (default=25f)
                        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                            return SPEED / displayMetrics.densityDpi
                        }
                    }
                    smoothScroller.targetPosition = position
                    startSmoothScroll(smoothScroller)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

         autoScrollAnother()
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_horizontal_ticker.layoutManager = layoutManager
        recycler_horizontal_ticker.adapter = stockTickerAdapter


        newsFeedHorizontalAdaper = NewsFeedHorizontalAdapter(horizontalNewsFeedList, object : NewsFeedHorizontalAdapter.ClickItemListener {
            override fun onClicked(position: Int) {
//                Navigation.findNavController(recycler_posts)
//                    .navigate(R.id.action_firstFragment_to_secondFragment)
            }

            override fun onProductLiked(position: Int, isLiked: Boolean) {
            }

        })

        val newsFeedHorizontalLayoutManager = LinearLayoutManager(requireContext())
        newsFeedHorizontalLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_horizontal_news.layoutManager = newsFeedHorizontalLayoutManager
        recycler_horizontal_news.adapter = newsFeedHorizontalAdaper

        newsAdapter = NewsAdapter(newsFeedList, object : NewsAdapter.ClickItemListener {
            override fun onClicked(position: Int) {
//                Navigation.findNavController(recycler_posts)
//                    .navigate(R.id.action_firstFragment_to_secondFragment)
            }

            override fun onProductLiked(position: Int, isLiked: Boolean) {
            }

        })

        val newsLinearLayoutManager = LinearLayoutManager(requireContext())
        newsLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_vertical_news.layoutManager = newsLinearLayoutManager
        recycler_vertical_news.adapter = newsAdapter

    }

    private fun autoScrollAnother() {
        var scrollCount = 0
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                recycler_horizontal_ticker.smoothScrollToPosition(scrollCount++)
                handler.postDelayed(this, 1000)
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    //subscribing to network live data
    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        mViewModel.stocksData.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let {
                        stocksList.addAll(it)
                        Log.d("stocks",it.toString())
                        stockTickerAdapter.notifyDataSetChanged()
                    }

                }
                Resource.Status.LOADING -> {
                    showProgressBar()
                }
                Resource.Status.ERROR -> {
                    hideProgressBar()

                    Snackbar.make(recycler_horizontal_ticker!!, it.message!!, Snackbar.LENGTH_SHORT)
                        .show()

                }
            }
        })

        mViewModel.newsFeed.observe(this) {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let{newsFeedResponse ->
                        newsFeedResponse.articles.let { articles ->
                            for (index in articles.indices) {
                                if (index < 6)
                                    horizontalNewsFeedList.add(articles[index])
                                else {
                                    newsFeedList.add(articles[index])
                                }
                            }
                            newsFeedHorizontalAdaper.notifyDataSetChanged()
                            newsAdapter.notifyDataSetChanged()
                            Log.d("articles", articles.toString())
                        }
                    }
                }
                Resource.Status.LOADING -> {
                    showProgressBar()
                }
                Resource.Status.ERROR -> {
                    hideProgressBar()

                    Snackbar.make(recycler_horizontal_ticker!!, it.message!!, Snackbar.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }


}