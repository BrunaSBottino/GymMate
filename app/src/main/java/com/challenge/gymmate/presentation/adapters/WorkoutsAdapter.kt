package com.challenge.gymmate.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.gymmate.data.firebaseAuth.FirebaseAuthRepository
import com.challenge.gymmate.data.firebaseStorage.FirebaseStorageRepository
import com.challenge.gymmate.data.firestore.FirestoreHelper
import com.challenge.gymmate.data.model.Workout
import com.challenge.gymmate.databinding.WorkoutsItemBinding
import com.challenge.gymmate.presentation.customViews.WorkoutDialog
import org.koin.java.KoinJavaComponent.get

class WorkoutsAdapter(private val allWorkouts : List<Workout>, private val updateScreen : () -> Unit)
    : RecyclerView.Adapter<WorkoutsAdapter.WorkoutsViewHolder>() {

    class WorkoutsViewHolder(val binding: WorkoutsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutsAdapter.WorkoutsViewHolder {
        return WorkoutsViewHolder(WorkoutsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WorkoutsAdapter.WorkoutsViewHolder, position: Int) {
        holder.binding.run {
            val currentWorkout = allWorkouts[position]
            runCatching {
                Glide.with(imageViewWallpaper).load(currentWorkout.posterURL).into(imageViewWallpaper)
            }.onFailure {

            }
            // ao clicar em um dos itens, mostra o WorkoutDialog
            root.setOnClickListener{
                WorkoutDialog(currentWorkout, root.context, updateScreen).show()
            }
            setupRecyclerView(recyclerViewDetails, currentWorkout)
            setupOtherViews(textViewTitle, textViewTime, currentWorkout)
        }
    }

    private fun setupOtherViews(
        textViewTitle: TextView,
        textViewTime: TextView,
        currentWorkout: Workout
    ) {
        currentWorkout.run {
            textViewTitle.text = title
            textViewTime.text = currentWorkout.time
        }
    }

    private fun setupRecyclerView(recyclerViewDetails: RecyclerView, currentWorkout: Workout) {
        recyclerViewDetails.run {
            adapter = DetailsAdapter(currentWorkout.details)
            layoutManager = LinearLayoutManager(
                recyclerViewDetails.context,
                RecyclerView.HORIZONTAL,
                true
            )
        }
    }

    override fun getItemCount(): Int = allWorkouts.size
}