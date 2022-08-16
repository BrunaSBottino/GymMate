package com.challenge.gymmate.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.gymmate.data.model.User
import com.challenge.gymmate.domain.session.FirebaseSession

class MainViewModel : ViewModel() {

    val user = MutableLiveData(User())

    fun fetchUser() {
        FirebaseSession.readUserFromDataBase {
            user.value = it
        }
    }
}
