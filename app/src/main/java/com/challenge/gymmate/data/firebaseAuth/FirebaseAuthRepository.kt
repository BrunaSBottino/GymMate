package com.challenge.gymmate.data.firebaseAuth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthRepository {

    private val authenticator: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser = authenticator.currentUser

    fun registerUser(email:String, password:String): Task<AuthResult> =
        authenticator.createUserWithEmailAndPassword(email, password)

    fun login(email:String, password:String): Task<AuthResult> =
        authenticator.signInWithEmailAndPassword(email, password)

    fun logout() {
        authenticator.signOut()
    }
}