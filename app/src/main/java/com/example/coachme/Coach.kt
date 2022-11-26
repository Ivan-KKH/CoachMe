package com.example.coachme

import android.graphics.drawable.Drawable

//rating is the accumulated rating sum got by the coach,
//then the final rating displayed is
// (rating/ rated_ppl) * 5
// Gonna remove the rated_ppl attribute
data class Coach(
    var user_id: Int,
    var coach_id: Int,
    var name: String,
    var image: Drawable?,
    var yearExp: String,
    var qualification: String,
    var rating: String,
    var bookmark: String,
    var rated_ppl: Int,
)