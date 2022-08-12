package com.challenge.gymmate.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.challenge.gymmate.R
import com.challenge.gymmate.databinding.FragmentLoginBinding
import com.challenge.gymmate.presentation.viewModels.LoginViewModel
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.firebase.auth.ktx.auth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModel()
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container)
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
                                // Abrir popup de erro de login
                            }
                        }
                    )
                }
            }
            buttonRegister.setOnClickListenerWithAnimation {
                navController.navigate(R.id.goToRegisterScreenAction)
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser!=null){
            navController.navigate(R.id.loginAction)
        }
    }
}