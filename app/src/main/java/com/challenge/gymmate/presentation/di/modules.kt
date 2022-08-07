package com.challenge.gymmate.presentation.di

import com.challenge.gymmate.data.firebaseAuth.FirebaseAuthRepository
import com.challenge.gymmate.data.firebaseStorage.FirebaseStorageRepository
import com.challenge.gymmate.data.firestore.FirestoreRepository
import com.challenge.gymmate.data.interactor.FirebaseStorageInteractor
import com.challenge.gymmate.data.interactor.FirestoreInteractor
import com.challenge.gymmate.presentation.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}

var repositoryModule = module {
    single { FirestoreRepository() }
    single { FirebaseStorageRepository() }
    single { FirebaseAuthRepository() }
}

var interactorModule = module {
    single { FirebaseStorageInteractor(get()) }
    single { FirestoreInteractor(get()) }
}