package com.challenge.gymmate.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.challenge.gymmate.R
import com.challenge.gymmate.databinding.FragmentHomeBinding
import com.challenge.gymmate.databinding.FragmentRegisterBinding
import com.challenge.gymmate.presentation.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        //endereço do link
//        https://firebasestorage.googleapis.com/v0/b/gym-mate-7e16c.appspot.com/o/img1.webp?alt=media&token=6f06730b-2463-444a-824b-8ac368f0a54d



        return binding.root
    }
}