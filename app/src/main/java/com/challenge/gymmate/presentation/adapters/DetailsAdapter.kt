package com.challenge.gymmate.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.gymmate.data.model.Detail
import com.challenge.gymmate.databinding.DetailItemBinding

class DetailsAdapter(val details : List<Detail>)
    : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    class DetailsViewHolder(val binding: DetailItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsAdapter.DetailsViewHolder {
        return DetailsViewHolder(DetailItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DetailsAdapter.DetailsViewHolder, position: Int) {
        holder.binding.textView.text = details[position].title
    }

    override fun getItemCount(): Int = details.size
}