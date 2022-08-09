package com.challenge.gymmate.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.challenge.gymmate.R
import com.challenge.gymmate.databinding.FragmentLoginBinding
import com.challenge.gymmate.presentation.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val navController = Navigation.findNavController(requireActivity(), R.id.fragment_container)
        binding.loginContainer.run {
            buttonLogin.setOnClickListenerWithAnimation {
                val email = editTextUsername.text
                val password = editTextPassword.text
                if (email.isNotBlank() && password.isNotBlank()) {
                    viewModel.login(
                        email = email,
                        password = password,
                        onFinish = { isSuccessful ->
                            if (isSuccessful) {
                                navController.navigate(R.id.loginAction)
                            } else {
                                navController.navigate(R.id.loginAction)
                            }
                        }
                    )
                }
            }
            buttonRegister.setOnClickListenerWithAnimation {
                navController.navigate(R.id.registerAction)
            }
            return binding.root
        }
    }

}