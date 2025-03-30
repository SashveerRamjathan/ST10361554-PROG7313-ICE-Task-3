package com.st10361554.quizapp

import UserScore
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.st10361554.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private lateinit var tvCategory: TextView
    private lateinit var tvScore: TextView
    private lateinit var btnMenu: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvCategory = binding.tvCategory
        tvScore = binding.tvScore
        btnMenu = binding.btnMenu

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val score = intent.getIntExtra("finalScore", 0)
        val categoryName = intent.getStringExtra("categoryName")

        tvCategory.text = categoryName
        tvScore.text = "$score points"


        val user = auth.currentUser

        val userId = user?.uid

        if (userId != null)
        {
            // create a new user score object
            val userScore = UserScore(
                userId = userId,
                categoryName = categoryName ?: "",
                score = score,
                timestamp = Timestamp.now()
            )

            // add the user score to the database
            firestore.collection("quiz-user-scores")
                .add(userScore)

                .addOnSuccessListener {
                    // display success message
                    Toast.makeText(this, "Score saved successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // display error message
                    Toast.makeText(this, "Error saving score", Toast.LENGTH_SHORT).show()
                }

        }


        btnMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}