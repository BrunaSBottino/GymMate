package com.challenge.gymmate.data.model

sealed class DataState {

    data class BackendError(val errorMessage:String="")
    data class Success<T>(val data:T)
    // Leia sobre genericos
    // Genericos representam basicamente entre qualquer coisa aqui contanto
    // que depois sua tipagem seja respeitada para evitar crashes

}
