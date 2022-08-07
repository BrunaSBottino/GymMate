package com.challenge.gymmate.presentation.di

import android.app.Application
import org.koin.core.context.startKoin

class GymMateApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules()
        }
    }
}
