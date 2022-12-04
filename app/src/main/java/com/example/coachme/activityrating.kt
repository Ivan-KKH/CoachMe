package com.example.coachme

import android.content.Intent
import android.media.Rating
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.coachme.Coach_reg3.Companion.FLASK_URL

class activityrating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        val coach_name = intent.getStringExtra("coach_name")
        val coach_id = intent.getIntExtra("coach_id", 0)
        val student_id = intent.getIntExtra("student_id", 0)
        val ratebutton = findViewById<Button>(R.id.ratebutton)
        val ratebar = findViewById<RatingBar>(R.id.listitemrating)
        val question = findViewById<TextView>(R.id.question_text)
        val currentUserFirstName = getSharedPreferences("userSharedPreference", MODE_PRIVATE)
            .getString("first_name", "")
        val rating = 0
        /* ratebar.setOnRatingBarChangeListener {ratebar, rating, fromUser ->
            Toast.makeText(this@activityrating, "Rating: $rating", Toast.LENGTH_SHORT).show()
        } */

        question.setText("How would You Rate $coach_name ?")

        ratebutton.setOnClickListener {
            val rating = ratebar.rating
            val coachintent = Intent(this, coachprofile::class.java)
            var url:String = FLASK_URL+"rate?&student_id=$student_id&coach_id=$coach_id&rating=$rating"

            var jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    print("send request")
                },
                { error ->
                    Log.e("MyActivity",error.toString())
                }
            )
            Volley.newRequestQueue(this).add(jsonObjectRequest)
            Toast.makeText(getApplicationContext(),"Thank you! You have rated Bob $rating stars", Toast.LENGTH_SHORT).show();
            intent.putExtra("coach_name", coach_name)
            intent.putExtra("coach_id", coach_id)
            intent.putExtra("student_id", student_id)
            startActivity(coachintent)
            overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);


        }
    }

}