package com.baraka.androidtask.ui.firstfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.baraka.androidtask.baseclasses.BaseViewModel
import com.baraka.androidtask.data.models.newsfeed.NewsFeedResponse
import com.baraka.androidtask.data.models.stocktickers.Stocks
import com.baraka.androidtask.data.models.stocktickers.StocksResponseItem
import com.baraka.androidtask.data.remote.Resource
import com.baraka.androidtask.data.remote.reporitory.MainRepository
import com.baraka.androidtask.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _stocks = MutableLiveData<Resource<List<Stocks>>>()
    val stocksData: LiveData<Resource<List<Stocks>>>
        get() = _stocks

    fun getStocksFromURL(){
        GlobalScope.launch {
            _stocks.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getStocks().let {
                    if (it.isNotEmpty()){
                        _stocks.postValue(Resource.success(it))
                    }else _stocks.postValue(Resource.error("error reading stocks", null))
                }

            }  else _stocks.postValue(Resource.error("No internet connection", null))
        }


    }


    private val _newsFeed = MutableLiveData<Resource<NewsFeedResponse>>()
    val newsFeed: LiveData<Resource<NewsFeedResponse>>
        get() = _newsFeed


    fun getNewsFromURL() {
        viewModelScope.launch {
            _newsFeed.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getNewsFeed().let {
                    if (it.isSuccessful) {
                        _newsFeed.postValue(Resource.success(it.body()!!))
                    } else _newsFeed.postValue(Resource.error(it.message(), null))
                }
            } else _newsFeed.postValue(Resource.error("No internet connection", null))
        }
    }

}