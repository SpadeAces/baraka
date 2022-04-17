package com.baraka.androidtask.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.baraka.androidtask.BR
import com.baraka.androidtask.R
import com.baraka.androidtask.baseclasses.BaseActivity
import com.baraka.androidtask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val layoutId: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var progress_bar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialising()
    }

    private fun initialising() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorWhite)
        progress_bar = findViewById(R.id.progress_bar)
    }

    open fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    open fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

}