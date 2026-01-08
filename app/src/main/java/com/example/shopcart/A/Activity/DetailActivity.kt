package com.example.shopcart.A.Activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.shopcart.A.Adapter.ColorAdapter
import com.example.shopcart.A.Adapter.PicsAdapter
import com.example.shopcart.A.Adapter.SizeAdapter
import com.example.shopcart.A.Helper.ManagmentCart
import com.example.shopcart.A.Model.ItemModel
import com.example.shopcart.R
import com.example.shopcart.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var itemModel: ItemModel
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        itemModel = intent.getSerializableExtra("object")!! as ItemModel

        setupViews()
        setupPicsList()
        setupColorsList()
        setupSizesList()

    }

    private fun setupSizesList() {
        val sizeList = itemModel.size.map { it}
        binding.sizeList.apply {
            adapter = SizeAdapter(sizeList as MutableList<String>)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupColorsList() {
        binding.colorList.adapter = ColorAdapter(itemModel.color)
        binding.colorList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupPicsList() {
        val picList = itemModel.picUrl.toList()
        binding.picList.apply {
            adapter = PicsAdapter(picList as MutableList<String>) {imageUrl->
                Glide.with(this@DetailActivity)
                    .load(imageUrl)
                    .into(binding.picMain)
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupViews()=with(binding) {
        tittleTxt.text = itemModel.title
        descriptionTxt.text = itemModel.description
        priceTxt.text = "₹${itemModel.price}"
        oldPriceTxt.text = "₹${itemModel.oldPrice}"
        oldPriceTxt.paintFlags = priceTxt.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        ratingTxt.text = "${itemModel.rating} Rating"
        numberItemTxt.text = itemModel.numberInCart.toString()

        updateTotalPrice()

        Glide.with(this@DetailActivity)
            .load(itemModel.picUrl.firstOrNull())
            .into(picMain)

        backBtn.setOnClickListener {
            finish()
        }

        plusBtn.setOnClickListener {
            numberItemTxt.text = "${++itemModel.numberInCart}"
            updateTotalPrice()
        }

        minuxBtn.setOnClickListener {
            if(itemModel.numberInCart>1){
                itemModel.numberInCart--
                numberItemTxt.text = "${itemModel.numberInCart}"
                updateTotalPrice()

            }
        }
        addToCartBtn.setOnClickListener {
            itemModel.numberInCart = 1
            managmentCart.insertFood(itemModel)
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }



    }

    private fun updateTotalPrice() {
        val totalPrice = itemModel.price * itemModel.numberInCart
        binding.totalPriceTxt.text = "₹$totalPrice"

    }
}