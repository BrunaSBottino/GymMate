package com.challenge.gymmate.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.challenge.gymmate.R
import com.challenge.gymmate.databinding.FragmentRegisterBinding
import com.challenge.gymmate.databinding.FragmentRegisterMainCardBinding
import com.challenge.gymmate.presentation.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.text.method.PasswordTransformationMethod as PasswordTransformationMethod1

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel : LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val navController = Navigation.findNavController(requireActivity(), R.id.fragment_container)
        binding.registerContainer.run {
            buttonRegister.setOnClickListenerWithAnimation {
                val email = editTextRegisterName.text
                val password = editTextPasswordRegister.text
                val passwordConfirm = editTextConfirmPasswordRegister.text
                if (password == passwordConfirm) {
                    if (email.isNotBlank()
                        && password.isNotBlank()
                        && passwordConfirm.isNotBlank()
                    ) {
                        viewModel.register(
                            email = email,
                            password = password,
                            onFinish = { isSuccessful ->
                                if (isSuccessful) {
                                    navController.navigate(R.id.registerAction)
                                }
                            }
                        )
                    }else{
                        //caso um dos campos esteja vazio
                    }
                }else{
                    //senha nao bate
                }
            }
        }

        return binding.root
    }
}