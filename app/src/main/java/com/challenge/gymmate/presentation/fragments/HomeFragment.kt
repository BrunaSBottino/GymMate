package com.challenge.gymmate.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.gymmate.R
import com.challenge.gymmate.data.model.User
import com.challenge.gymmate.databinding.FragmentHomeBinding
import com.challenge.gymmate.presentation.adapters.WorkoutsAdapter
import com.challenge.gymmate.presentation.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setObservers()
        viewModel.fetchUser()
        binding.floatingButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.newWorkoutAction)
        }
        return binding.root
    }

    private fun setObservers() {
        viewModel.user.observe(viewLifecycleOwner){
            setupWorkoutsAdapter(it)
        }
    }

    private fun setupWorkoutsAdapter(user: User) {
        binding.recyclerViewWorkouts.run {
            adapter = user.allWorkouts?.let { WorkoutsAdapter(it) }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


}
