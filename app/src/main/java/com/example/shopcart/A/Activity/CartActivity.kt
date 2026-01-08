package com.example.shopcart.A.Activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopcart.A.Adapter.CartAdapter
import com.example.shopcart.A.Helper.ManagmentCart
import com.example.shopcart.R
import com.example.shopcart.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var  managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        managmentCart = ManagmentCart(this)

        initView()
        calculateCart()
        initCartList()
        checkout()
    }

    private fun checkout() {
        binding.checkout.setOnClickListener {
            Toast.makeText(this,"Your Order will be delivered", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }

    private fun initView() {
        binding.backBtn.setOnClickListener { finish() }
    }
    private fun initCartList() {
        binding.apply {
            viewCart.layoutManager = LinearLayoutManager(
                this@CartActivity,
                LinearLayoutManager.VERTICAL, false
            )

            viewCart.adapter =
                CartAdapter(managmentCart.getListCart(), this@CartActivity, object :
                    ChangeNumberItemsListener {
                    override fun onChanged() {
                        calculateCart()
                    }
                })
            emptyTxt.visibility =
                if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE

            scrollView2.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }
    private fun calculateCart(){
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery)*100)/100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100

        with(binding){
            totalfeeTxt.text = "₹$itemTotal"
            DeliveryTxt.text = "₹$delivery"
            totalTxt.text = "₹$total"
            taxTxt.text = "₹$tax"

        }
    }
}