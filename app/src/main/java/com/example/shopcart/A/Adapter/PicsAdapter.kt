package com.example.shopcart.A.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopcart.A.Adapter.PicsAdapter.Viewholder
import com.example.shopcart.R
import com.example.shopcart.databinding.ViewholderPicsBinding

class PicsAdapter(val items: MutableList<String>,private val onImageSelected: (String) -> Unit) :
    RecyclerView.Adapter<PicsAdapter.Viewholder>() {
    inner class Viewholder(val binding: ViewholderPicsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        context  = parent.context
        val binding = ViewholderPicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PicsAdapter.Viewholder, position: Int) {
        Glide.with(context)
            .load(items[position])
            .into(holder.binding.pic)

        // Set background based on selection
        if (selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }

        // Click listener to handle selection
        holder.itemView.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            if (lastSelectedPosition != -1) {
                holder.itemView.post {
                    notifyItemChanged(lastSelectedPosition)
                }
            }

            holder.itemView.post {
                notifyItemChanged(selectedPosition)
            }

            onImageSelected(items[selectedPosition])
        }
    }

    override fun getItemCount(): Int = items.size

}