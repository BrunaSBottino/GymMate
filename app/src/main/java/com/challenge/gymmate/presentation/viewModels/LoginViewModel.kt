package com.challenge.gymmate.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.gymmate.data.firebaseAuth.FirebaseAuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val firebaseAuthRepository: FirebaseAuthRepository): ViewModel() {



    fun register(
        email:String,
        password:String,
        onFinish : (isSuccessful: Boolean) -> Unit
    ){
        viewModelScope.launch {
            val isSuccessful = firebaseAuthRepository.registerUser(email, password)
            onFinish(isSuccessful)
        }
    }

    fun login(
        email: String,
        password: String,
        onFinish: (isSuccessful: Boolean) -> Unit
    ){
        viewModelScope.launch {
            val isSuccessful = firebaseAuthRepository.login(email, password)
            onFinish(isSuccessful)
        }
    }


}
