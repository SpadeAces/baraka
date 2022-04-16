package com.baraka.androidtask.ui.firstfragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.baraka.androidtask.BR
import com.baraka.androidtask.R
import com.baraka.androidtask.baseclasses.BaseFragment
import com.baraka.androidtask.data.local.datastore.DataStoreProvider
import com.baraka.androidtask.data.models.PostsResponseItem
import com.baraka.androidtask.data.remote.Resource
import com.baraka.androidtask.databinding.FirstFragmentBinding
import com.baraka.androidtask.ui.firstfragment.adapter.PostsRecyclerAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.first_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : BaseFragment<FirstFragmentBinding, FirstViewModel>() {

    override val layoutId: Int
        get() = R.layout.first_fragment
    override val viewModel: Class<FirstViewModel>
        get() = FirstViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var adapter: PostsRecyclerAdapter
    private var postsList: ArrayList<PostsResponseItem> = ArrayList()
    lateinit var dataStoreProvider: DataStoreProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get reference to our Data Store Provider class
        dataStoreProvider = DataStoreProvider(requireContext())


        subscribeToObserveDataStore()

        //calling api
        mViewModel.fetchPostsFromApi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialising()
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()

        mViewModel.btnClick.observe(this, Observer {

            //observing data from edittext
            mViewModel.myedittext.get()?.let {

                //setting data to textview
                mViewModel.myName.set(it)

                //saving data to data store
                //Stores the values
                GlobalScope.launch {
                    dataStoreProvider.storeData(false, it)
                }
            }
        })
    }

    private fun subscribeToObserveDataStore() {

        //observing data from data store and showing
        dataStoreProvider.userNameFlow.asLiveData().observe(this, Observer {
            mViewModel.myName.set(it)
        })

    }


    private fun initialising() {

        adapter = PostsRecyclerAdapter(postsList, object : PostsRecyclerAdapter.ClickItemListener {
            override fun onClicked(position: Int) {
                Navigation.findNavController(recycler_posts)
                    .navigate(R.id.action_firstFragment_to_secondFragment)
            }

            override fun onProductLiked(position: Int, isLiked: Boolean) {
            }

        })

        recycler_posts.layoutManager = LinearLayoutManager(requireContext())
        recycler_posts.adapter = adapter

    }

    //subscribing to network live data
    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        mViewModel.postsData.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let {
                        postsList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }

                }
                Resource.Status.LOADING -> {
                    showProgressBar()
                }
                Resource.Status.ERROR -> {
                    hideProgressBar()

                    Snackbar.make(recycler_posts!!, it.message!!, Snackbar.LENGTH_SHORT)
                        .show()

                }
            }
        })
    }


}