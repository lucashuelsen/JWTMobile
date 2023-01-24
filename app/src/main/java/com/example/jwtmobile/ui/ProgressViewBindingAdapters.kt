package com.example.jwtmobile.ui

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.example.jwtmobile.util.ProgressView


@InverseBindingAdapter(attribute = "app:isLoading")
fun getIsLoading(view: ProgressView): Boolean {
    return view.isLoading
}

@BindingAdapter("app:isLoading")
fun setIsLoading(view: ProgressView, isLoading: Boolean?) {
    view.isLoading = isLoading ?: false
}