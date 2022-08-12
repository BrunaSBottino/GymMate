package com.challenge.gymmate.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.gymmate.data.firebaseAuth.FirebaseAuthRepository
import com.challenge.gymmate.data.firestore.FirestoreHelper
import com.challenge.gymmate.data.model.User
import com.challenge.gymmate.data.session.GymMateSession
import kotlinx.coroutines.launch

class LoginViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val firestoreHelper: FirestoreHelper
    ): ViewModel() {

    fun register(
        email:String,
        password:String,
        onFinish : (isSuccessful: Boolean) -> Unit
    ){
        viewModelScope.launch {
            firebaseAuthRepository.registerUser(email, password).addOnCompleteListener {
                onFinish(it.isSuccessful)
                firestoreHelper.saveUserIntoDatabase(User(email))
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
