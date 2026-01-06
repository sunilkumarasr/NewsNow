package com.example.newsatnow.adapter

import android.annotation.SuppressLint
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

    private var selectedPosition = 0
    private var lastSelectedPosition = -1

    // This function inflated the list_item and fits it into the Recycler View Widget
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        CatItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    // This funciton binds the Content with the components of the Recycler View.
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.binding.catText.text = texts[position].name

        // Apply selected / unselected UI
        if (selectedPosition == position) {
            holder.binding.catText.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.app_main_color)
            )
            holder.binding.catText.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.white)
            )
        } else {
            holder.binding.catText.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.white)
            )
            holder.binding.catText.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.app_gray_color)
            )
        }

        holder.itemView.setOnClickListener {
            if (selectedPosition == position) return@setOnClickListener

            lastSelectedPosition = selectedPosition
            selectedPosition = position

            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            onClickListener?.onClick(position, texts[position])
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
