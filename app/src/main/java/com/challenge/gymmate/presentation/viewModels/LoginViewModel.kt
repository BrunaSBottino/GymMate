package com.challenge.gymmate.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.gymmate.data.model.User
import com.challenge.gymmate.domain.session.FirebaseSession.firebaseAuthRepository
import com.challenge.gymmate.domain.session.FirebaseSession.firestoreHelper
import kotlinx.coroutines.launch

class LoginViewModel(): ViewModel() {

    fun register(
        email:String,
        password:String,
        onFinish : (isSuccessful: Boolean) -> Unit
    ){
        viewModelScope.launch {
            firebaseAuthRepository.registerUser(email, password).addOnCompleteListener {
                onFinish(it.isSuccessful)
                if (it.isSuccessful) {
                    firestoreHelper.saveUserIntoDatabase(User(email))
                }
            }
        }
    }

    fun login(
        email: String,
        password: String,
        onFinish: (isSuccessful: Boolean) -> Unit
    ){
        viewModelScope.launch {
            firebaseAuthRepository.login(email, password).addOnCompleteListener {
                onFinish(it.isSuccessful)
            }
        }
    }
}
