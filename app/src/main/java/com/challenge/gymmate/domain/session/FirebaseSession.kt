package com.challenge.gymmate.domain.session

import com.challenge.gymmate.data.firebaseAuth.FirebaseAuthRepository
import com.challenge.gymmate.data.firestore.FirestoreHelper
import com.challenge.gymmate.data.model.User
import com.challenge.gymmate.data.model.Workout

object FirebaseSession {

    val firebaseAuthRepository = FirebaseAuthRepository()
    val firestoreHelper = FirestoreHelper()

    val authUser = firebaseAuthRepository.currentUser
    var firestoreUser: User? = null

    const val WORKOUTS_FIELD = "allWorkouts"

    fun addNewWorkout(workout: Workout, onFinished: (isSuccessful: Boolean) -> Unit = {}) {
        val oldWorkouts = firestoreUser?.allWorkouts ?: listOf()
        val newWorkouts = arrayListOf(workout)
        newWorkouts.addAll(oldWorkouts)
        updateUserData(newWorkouts, WORKOUTS_FIELD, onFinished)
    }

    fun deleteWorkout(workout: Workout, onFinished: (isSuccessful: Boolean) -> Unit = {}) {
        val oldWorkouts = firestoreUser?.allWorkouts ?: listOf()
        val newWorkouts = oldWorkouts.filter { it != workout }
        updateUserData(newWorkouts, WORKOUTS_FIELD, onFinished)
    }

    private fun updateUserData(
        newData: Any,
        field: String,
        onFinished: (isSuccessful: Boolean) -> Unit = {}
    ) =
        authUser?.email?.let {
            firestoreHelper.updateUserData(it, newData, field).addOnCompleteListener {
                onFinished(it.isSuccessful)
            }
        }

    fun readUserFromDataBase(onFinished: (isSuccessful: User) -> Unit = {}) = authUser?.email?.let {
        firestoreHelper.readUserFromDatabase(it).addOnSuccessListener { userSnapshot ->
            userSnapshot.toObject(User::class.java)?.let {
                onFinished(it)
                firestoreUser = it
            }
        }
    }
}
