package com.challenge.gymmate.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.gymmate.databinding.DetailItemBinding

class DetailAdapter(val details : List<String>)
    : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    class DetailViewHolder(val binding: DetailItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailAdapter.DetailViewHolder {
        return DetailViewHolder(DetailItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DetailAdapter.DetailViewHolder, position: Int) {
        holder.binding.textView.text = details[position]
    }

    override fun getItemCount(): Int = details.size
}