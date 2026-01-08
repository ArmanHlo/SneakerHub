package com.example.shopcart.A.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shopcart.A.Model.BrandModel
import com.example.shopcart.A.Model.ItemModel
import com.example.shopcart.A.Model.SliderModel
import com.example.shopcart.A.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository ()

    val brands: LiveData<MutableList<BrandModel>> = repository.brands
    val banners: LiveData<List<SliderModel>> = repository.banners
    val popular: LiveData<List<ItemModel>> = repository.popular

    fun loadBrands() = repository.loadBrands()
    fun loadBanners() = repository.loadBanners()
    fun loadPopular() = repository.loadPopular()
}