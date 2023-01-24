package com.example.jwtmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.jwtmobile.R
import com.example.jwtmobile.bo.SAPPriceItemHolder
import com.example.jwtmobile.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViewModel()
        setupObservers()
    }

    private fun setupBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding?.lifecycleOwner = this
    }

    private fun setupViewModel(){
        val customViewModelFactory = MainViewModelFactory(MutableLiveData())
        val viewModelProvider =  ViewModelProvider(this, customViewModelFactory)

        mViewModel = viewModelProvider.get(MainViewModel::class.java)
        mBinding?.viewModel = mViewModel
    }

    private fun setupObservers() {
        mViewModel?.amount?.observe(this){
            val amount = if(it.isNullOrBlank()) BigDecimal.ZERO else it.toBigDecimal()
            val sapPrincing = SAPPriceItemHolder(amount, BigDecimal((100)))

            mViewModel?.dataLoader?.load(mViewModel?.pricing){
                Thread.sleep(1000)
                sapPrincing
            }
        }
    }
}