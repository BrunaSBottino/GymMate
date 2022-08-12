package com.challenge.gymmate.presentation.di

import com.challenge.gymmate.data.firebaseAuth.FirebaseAuthRepository
import com.challenge.gymmate.data.firebaseStorage.FirebaseStorageRepository
import com.challenge.gymmate.data.firestore.FirestoreHelper
import com.challenge.gymmate.presentation.viewModels.MainViewModel
import com.challenge.gymmate.presentation.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get(), get()) }
}

var repositoryModule = module {
    single { FirestoreHelper() }
    single { FirebaseStorageRepository() }
    single { FirebaseAuthRepository() }
}