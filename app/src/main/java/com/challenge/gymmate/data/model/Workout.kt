package com.challenge.gymmate.data.model

data class Workout(
    val title : String="",
    val description : String="",
    val time : String="",
    val details : ArrayList<Detail> = arrayListOf(),
    val posterURL : String=""
)