package com.challenge.gymmate.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.gymmate.data.firebaseAuth.FirebaseAuthRepository
import com.challenge.gymmate.data.firebaseStorage.FirebaseStorageRepository
import com.challenge.gymmate.data.firestore.FirestoreHelper
import com.challenge.gymmate.data.model.User
import com.challenge.gymmate.data.model.Workout

class MainViewModel(
    val storageRepository: FirebaseStorageRepository,
    val firestoreHelper: FirestoreHelper,
    val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    val user = MutableLiveData(User())

    val currentUserAuth = firebaseAuthRepository.currentUser

    fun fetchUser(){
        val email = currentUserAuth?.email
        email?.let {
            firestoreHelper.readUserFromDatabase(email).addOnSuccessListener {
                user.value = it.toObject(User::class.java)
            }
        }
    }

    fun addNewWorkout(workout: Workout) {
        val oldWorkouts = user.value?.allWorkouts
        val newWorkouts = arrayListOf(workout)
        if (oldWorkouts != null) {
            newWorkouts.addAll(oldWorkouts)
        }
        currentUserAuth?.email?.let {
            firestoreHelper.updateUserData(it, newWorkouts, WORKOUTS_FIELD)
        }
    }


    companion object {
        const val WORKOUTS_FIELD = "allWorkouts"
    }
}
