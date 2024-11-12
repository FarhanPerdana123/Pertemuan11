package com.example.pertemuan11

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nameTextView: TextView = findViewById(R.id.name_text)
        val emailTextView: TextView = findViewById(R.id.email_text)
        val avatarImageView: ImageView = findViewById(R.id.profile_image)

        // Retrieve data from intent
        val userName = intent.getStringExtra("user_name")
        val userEmail = intent.getStringExtra("user_email")
        val userAvatar = intent.getStringExtra("user_avatar")

        // Set data to views
        nameTextView.text = userName
        emailTextView.text = userEmail
        Picasso.get().load(userAvatar).into(avatarImageView)
    }
}