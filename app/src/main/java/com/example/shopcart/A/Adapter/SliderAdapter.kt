package com.example.shopcart.A.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.shopcart.A.Model.SliderModel
import com.example.shopcart.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewholder>() {

    inner class SliderViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagesView: ImageView = itemView.findViewById(R.id.imageSlider)

        fun setImage(sliderItem: SliderModel, context: Context) {
            val requestOptions = RequestOptions().transform(CenterInside())
            Glide.with(context)
                .load(sliderItem.url)
                .apply(requestOptions)
                .into(imagesView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item_container, parent, false)
        return SliderViewholder(view)
    }

    override fun onBindViewHolder(holder: SliderViewholder, position: Int) {
        holder.setImage(sliderItems[position], holder.itemView.context)

        // Optional: for infinite scroll behavior (comment out if not needed)
        /*
        if (position == sliderItems.size - 2) {
            viewPager2.post(runnable)
        }
        */
    }

    override fun getItemCount() = sliderItems.size

    // Optional infinite scroll logic
    private val runnable = Runnable {
        val newList = ArrayList(sliderItems)
        newList.addAll(sliderItems)
        sliderItems = newList
        notifyDataSetChanged()
    }
}








//package com.example.shopcart.A.Adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.ViewPager2
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.bitmap.CenterInside
//import com.bumptech.glide.request.RequestOptions
//import com.example.shopcart.A.Model.SliderModel
//import com.example.shopcart.R
//
//class SliderAdapter(
//    private var sliderItems:List<SliderModel>,
//    private val viewPager2: ViewPager2
//): RecyclerView.Adapter<SliderAdapter.SliderViewholder>() {
//    class SliderViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private val imagesView: ImageView=itemView.findViewById(R.id.imageSlider)
//
//        fun setImage(sliderItems: SliderModel,context: Context){
//            val requestOption= RequestOptions().transform(CenterInside())
//            Glide.with(context)
//                .load(sliderItems.url)
//                .apply(requestOption)
//                .into(imagesView)
//
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): SliderAdapter.SliderViewholder {
//        val view = LayoutInflater.from(parent.context)
//        .inflate(R.layout.slider_item_container,parent,false)
//        return SliderViewholder(itemView=view)
//    }
//
//    override fun onBindViewHolder(holder:SliderViewholder, position: Int) {
//        holder.setImage(sliderItems=sliderItems[position],holder.itemView.context)
//        if(position==sliderItems.lastIndex-1){
//            viewPager2.post(runnable)
//        }
//    }
//
//    override fun getItemCount() = sliderItems.size
//    private val runnable = Runnable{
//        sliderItems = sliderItems
//        notifyDataSetChanged()
//    }
//}


