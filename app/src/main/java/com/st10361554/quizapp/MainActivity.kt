package com.st10361554.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.st10361554.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    // Buttons
    private lateinit var btnSignOut: Button

    // Text Views
    private lateinit var tvGreeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialise buttons
        btnSignOut = binding.btnSignOut

        // initialise text views
        tvGreeting = binding.tvGreeting

        // initialise firebase auth
        auth = FirebaseAuth.getInstance()

        // get current user
        val user: FirebaseUser? = auth.currentUser

        // check if user is not null
        if (user != null)
        {
            // user is signed in
            tvGreeting.text = "Welcome, ${user.email}"
        }

        // sign out button click
        btnSignOut.setOnClickListener {

            auth.signOut()

            // check if user is signed out
            if (auth.currentUser == null)
            {
                // display message
                Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()

                // redirect to login activity
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else
            {
                // display error message
                Toast.makeText(this, "Error signing out", Toast.LENGTH_SHORT).show()

                // log the error
                Log.d("MainActivity", "Error signing out: ${auth.currentUser?.email}")
            }
        }

    }
}