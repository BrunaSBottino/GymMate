package com.challenge.gymmate.presentation.di

import com.challenge.gymmate.data.firebaseStorage.FirebaseStorageRepository
import com.challenge.gymmate.data.firestore.FirestoreRepository
import com.challenge.gymmate.data.interactor.FirebaseStorageInteractor
import com.challenge.gymmate.data.interactor.FirestoreInteractor
import com.challenge.gymmate.presentation.viewModels.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { SharedViewModel(get()) }
}

var repositoryModule = module {
    factory { FirestoreRepository() }
    factory { FirebaseStorageRepository() }
}

var interactorModule = module {
    factory { FirebaseStorageInteractor(get()) }
    factory { FirestoreInteractor(get()) }
}