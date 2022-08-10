package com.challenge.gymmate.presentation.fragments

import androidx.lifecycle.ViewModel
import com.challenge.gymmate.data.interactor.FirebaseStorageInteractor
import com.challenge.gymmate.data.interactor.FirestoreInteractor

class MainViewModel(
    val storageInteractor: FirebaseStorageInteractor,
    val firestoreInteractor: FirestoreInteractor
) : ViewModel() {

}
