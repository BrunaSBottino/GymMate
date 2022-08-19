package com.challenge.gymmate.domain.di

import com.challenge.gymmate.presentation.viewModels.LoginViewModel
import com.challenge.gymmate.presentation.viewModels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
}
