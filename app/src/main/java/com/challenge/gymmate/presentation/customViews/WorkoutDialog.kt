package com.challenge.gymmate.presentation.customViews

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.challenge.gymmate.data.model.Workout
import com.challenge.gymmate.domain.session.FirebaseSession
import com.challenge.gymmate.databinding.DialogWorkoutBinding

class WorkoutDialog(
    val workout: Workout,
    context: Context,
    val updateScreen:() -> Unit
): Dialog(context) {

    lateinit var binding: DialogWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogWorkoutBinding.inflate(layoutInflater)
        binding.run {
            setContentView(root)
            textViewTitle.text = workout.title
            textViewTimeWorkout.text = workout.time
            buttonOk.setOnClickListenerWithAnimation {
                dismiss()
            }
            buttonDelete.setOnClickListenerWithAnimation{
                FirebaseSession.deleteWorkout(workout){ taskSuccessful ->
                    if(taskSuccessful){
                        updateScreen()
                        dismiss()
                    } else {
                        showMessage()
                    }
                }
            }
        }
    }

    private fun showMessage() {
        Toast.makeText(context, "Erro de conexão!", Toast.LENGTH_SHORT).show()
    }
}