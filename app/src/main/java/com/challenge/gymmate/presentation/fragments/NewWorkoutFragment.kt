package com.challenge.gymmate.presentation.fragments

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.challenge.gymmate.R
import com.challenge.gymmate.data.model.Detail
import com.challenge.gymmate.data.model.Workout
import com.challenge.gymmate.databinding.FragmentNewWorkoutBinding
import com.challenge.gymmate.presentation.customViews.DefaultEditText
import com.challenge.gymmate.presentation.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class NewWorkoutFragment : Fragment() {

    private lateinit var binding: FragmentNewWorkoutBinding
    private val detailsViews = ArrayList<DefaultEditText>()
    private lateinit var lastViewInConstraint : View
    private val viewModel : MainViewModel by sharedViewModel()
    private var time : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewWorkoutBinding.inflate(layoutInflater)
        lastViewInConstraint = binding.buttonWorkoutDate
        binding.run {
            buttonAddDetail.setOnClickListenerWithAnimation {
                val newDetailView = DefaultEditText(requireContext(), null)
                newDetailView.setHint("Digite uma nova descrição")
                detailsViews.add(newDetailView)
                setConstraintsForNewDescriptionView(newDetailView)
            }
            buttonSaveWorkout.setOnClickListenerWithAnimation {
                val title = editTextWorkoutTitle.text
                if (title.isNotBlank()) {
                    val description = editTextWorkoutDescription.text
                    val detailsList = arrayListOf<Detail>()
                    detailsViews.forEach {
                        if (it.text.isNotBlank()) {
                            val detail = Detail(it.text)
                            detailsList.add(detail)
                        }
                    }
                    time?.let {
                        val workout =
                        Workout(title, description, it, detailsList, getRandomPosterUrl())
                        viewModel.addNewWorkout(workout)
                    }
                }
            }
            buttonWorkoutDate.setOnClickListenerWithAnimation {
                inflateTimePickerDialog()
            }
        }


        return binding.root
    }

    private fun getRandomPosterUrl(): String {
        return "https://firebasestorage.googleapis.com/v0/b/gym-mate-7e16c.appspot.com/o/img1.webp?alt=media&token=6f06730b-2463-444a-824b-8ac368f0a54d"
    }

    private fun inflateTimePickerDialog() {
        val listener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            val formattedMinute = if (minute<10){
                "0$minute"
            } else { minute }
            binding.buttonWorkoutDate.run {
                val black = ContextCompat.getColor(context, R.color.black)
                setText("$hour : $formattedMinute", black)
                val color = ContextCompat.getColor(context, R.color.white)
                setColor(color)
            }
            time = "$hour : $formattedMinute"
        }
        val c = Calendar.getInstance()
        val mHour = c.get(Calendar.HOUR_OF_DAY)
        val mMinute = c.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(requireContext(), listener, mHour, mMinute, true)
        timePickerDialog.show()
    }

    private fun setConstraintsForNewDescriptionView(newDetailView: DefaultEditText) {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val set = ConstraintSet()
        newDetailView.id = View.generateViewId()
        binding.constraintLayout.addView(newDetailView, layoutParams)
        set.clone(binding.constraintLayout)
        val margin = dpToPx(20.0f, requireContext())
        set.connect(newDetailView.id, ConstraintSet.TOP, lastViewInConstraint.id, ConstraintSet.BOTTOM, margin)
        set.connect(newDetailView.id, ConstraintSet.START, binding.constraintLayout.id, ConstraintSet.START, margin)
        set.connect(newDetailView.id, ConstraintSet.END, binding.constraintLayout.id, ConstraintSet.END, margin)
        set.applyTo(binding.constraintLayout)
        lastViewInConstraint = newDetailView
    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.getResources().getDisplayMetrics()
        ).toInt()
    }
}