package com.example.newsatnow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsatnow.R
import com.example.newsatnow.databinding.CatItemsBinding
import com.example.newsatnow.model.Category


class CatAdapter(// List that holds every item to be displayed in RecyclerView
    var texts: ArrayList<Category>
) : RecyclerView.Adapter<CatAdapter.ViewHolder?>() {
    private var onClickListener: CatAdapter.OnClickListener? = null
    var selectedPosition: Int = -1
    var lastSelectedPosition: Int = -1
    // This function inflated the list_item and fits it into the Recycler View Widget
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        CatItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    // This funciton binds the Content with the components of the Recycler View.
    override fun onBindViewHolder(holder: CatAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.catText.text = texts[position].name
        holder.binding.catText.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        holder.binding.catText.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.app_gray_color))
        holder.itemView.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.getBindingAdapterPosition()
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            //notifyItemChanged(position)
            onClickListener?.onClick(position,texts[position])
        }
        if (selectedPosition == holder.getBindingAdapterPosition()) {
            holder.binding.catText.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.app_main_color))
            holder.binding.catText.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }else {
            holder.binding.catText.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            holder.binding.catText.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.app_gray_color))
        }

    }

    override fun getItemCount(): Int {
        return texts.size
    }

    inner class ViewHolder(val binding: CatItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    // Set the click listener for the adapter
    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    // Interface for the click listener
    interface OnClickListener {
        fun onClick(position: Int, model: Category)
    }

}
