package com.challenge.gymmate.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.gymmate.data.model.Workout
import com.challenge.gymmate.databinding.TrainItemBinding

class TrainAdapter(val allWorkouts : List<Workout>)
    : RecyclerView.Adapter<TrainAdapter.TrainViewHolder>() {

    class TrainViewHolder(val binding: TrainItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainAdapter.TrainViewHolder {
        return TrainViewHolder(TrainItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TrainAdapter.TrainViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = allWorkouts.size
}