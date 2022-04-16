package com.baraka.androidtask.ui.activity

import com.baraka.androidtask.baseclasses.BaseViewModel
import com.baraka.androidtask.data.remote.reporitory.MainRepository
import com.baraka.androidtask.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


}
