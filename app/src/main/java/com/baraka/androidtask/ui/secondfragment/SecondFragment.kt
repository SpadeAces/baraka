package com.baraka.androidtask.ui.secondfragment

import com.baraka.androidtask.BR
import com.baraka.androidtask.R
import com.baraka.androidtask.baseclasses.BaseFragment
import com.baraka.androidtask.databinding.LayoutSecondBinding

class SecondFragment : BaseFragment<LayoutSecondBinding, SecondViewModel>() {

    override val layoutId: Int
        get() = R.layout.layout_second
    override val viewModel: Class<SecondViewModel>
        get() = SecondViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel
}