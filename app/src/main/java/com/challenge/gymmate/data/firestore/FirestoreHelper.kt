package com.challenge.gymmate.data.firestore

import com.challenge.gymmate.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreHelper {

    private val db = Firebase.firestore

    fun saveUserIntoDatabase(user: User): Task<Void> {
        return db.collection("Users")
            .document(user.email.lowercase())
            .set(user)
    }

    fun readUserFromDatabase(
        email: String
    ): Task<DocumentSnapshot> {
        return db.collection("Users").document(email).get()
    }

    fun updateUserData(email: String, newData: Any, field: String): Task<Void> {
        return db.collection("Users").document(email).update(field, newData)
    }
}
