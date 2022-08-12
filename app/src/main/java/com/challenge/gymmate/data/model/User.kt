package com.challenge.gymmate.data.model

import java.io.Serializable

data class User(
    val email: String = "",
    var allWorkouts: List<Workout>? = null
):Serializable