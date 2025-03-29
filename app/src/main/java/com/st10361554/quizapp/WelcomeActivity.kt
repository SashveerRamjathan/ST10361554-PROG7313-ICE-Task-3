package com.st10361554.quizapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.st10361554.quizapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize firebase auth
        auth = FirebaseAuth.getInstance()

        // get the current user
        val currentUser: FirebaseUser? = auth.currentUser

        // Next button click
        binding.btnNext.setOnClickListener{

        }


    }
}