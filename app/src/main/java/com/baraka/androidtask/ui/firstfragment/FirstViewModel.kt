package com.baraka.androidtask.ui.firstfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.baraka.androidtask.baseclasses.BaseViewModel
import com.baraka.androidtask.data.models.stocktickers.StocksResponse
import com.baraka.androidtask.data.models.stocktickers.StocksResponseItem
import com.baraka.androidtask.data.remote.Resource
import com.baraka.androidtask.data.remote.reporitory.MainRepository
import com.baraka.androidtask.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _stocks = MutableLiveData<Resource<List<StocksResponseItem>>>()
    val stocksData: LiveData<Resource<List<StocksResponseItem>>>
        get() = _stocks



//    fun fetchStocksFromURL() {
//        viewModelScope.launch {
//            _stocks.postValue(Resource.loading(null))
//            if (networkHelper.isNetworkConnected()) {
//                mainRepository.getStocks().let {
//                    if (it.isSuccessful) {
//                        _stocks.postValue(Resource.success(it.body()!!))
//                    } else _stocks.postValue(Resource.error(it.message(), null))
//                }
//            } else _stocks.postValue(Resource.error("No internet connection", null))
//        }
//    }

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

}