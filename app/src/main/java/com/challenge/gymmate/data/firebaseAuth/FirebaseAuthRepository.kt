package com.challenge.gymmate.data.firebaseAuth

import android.util.Log
import com.challenge.gymmate.data.model.BackendException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseAuthRepository {

    private val authenticator: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email:String, password:String): Boolean {
        return withContext(Dispatchers.IO){
            try {
                val task = authenticator.createUserWithEmailAndPassword(email, password)
                task.isSuccessful
            } catch (e: BackendException){
                Log.e("Error: ", e.message)
                false
            }
        }
    }

    suspend fun login(email:String, password:String):Boolean{
        return withContext(Dispatchers.IO){
            try {
                val task = authenticator.signInWithEmailAndPassword(email, password)
                task.isSuccessful
            } catch (e: BackendException){
                Log.e("Error: ", e.message)
                false
            }
        }
    }

    suspend fun logout() {
        authenticator.signOut()
    }
}