package com.example.shopcart.A.Model

import java.io.Serializable

data class ItemModel(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = arrayListOf(),
    var size: ArrayList<String> = ArrayList(),
    var color: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var oldPrice:Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 1
): Serializable
