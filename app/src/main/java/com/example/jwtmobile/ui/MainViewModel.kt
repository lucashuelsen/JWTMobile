package com.example.jwtmobile.ui

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.example.jwtmobile.util.IPriceItemHolder
import com.example.jwtmobile.util.QueueMutableLiveDataLoader
import java.math.BigDecimal

class MainViewModel(
    val pricing: MutableLiveData<IPriceItemHolder?> = MutableLiveData()
) : ViewModel() {
    val unitValue = MediatorLiveData<BigDecimal>()
    val totalValue = MediatorLiveData<BigDecimal>()
    val taxValue = MediatorLiveData<BigDecimal>()
    val amount = MutableLiveData<String>()
    val dataLoader = QueueMutableLiveDataLoader()


    init {
        unitValue.addSource(pricing) {
            unitValue.value = it?.getUnitValue()
        }

        totalValue.addSource(pricing) {
            totalValue.value = it?.getTotalValue()
        }

        taxValue.addSource(pricing) {
            taxValue.value = it?.getTaxValue()
        }
    }
}

class MainViewModelFactory(val pricing: MutableLiveData<IPriceItemHolder?>) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(pricing) as T
            else -> super.create(modelClass)
        }
    }
}