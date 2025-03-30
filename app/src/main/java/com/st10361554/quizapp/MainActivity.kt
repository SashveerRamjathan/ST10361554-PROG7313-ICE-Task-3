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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.st10361554.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    // Buttons
    private lateinit var btnSignOut: Button
    private lateinit var btnGK: Button
    private lateinit var btnCS: Button
    private lateinit var btnSN: Button
    private lateinit var btnMPC: Button

    // Text Views
    private lateinit var tvGreeting: TextView
    private lateinit var tvScoreGK: TextView
    private lateinit var tvScoreCS: TextView
    private lateinit var tvScoreSN: TextView
    private lateinit var tvScoreMPC: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // initialise buttons
        btnSignOut = binding.btnSignOut
        btnGK = binding.btnGk
        btnCS = binding.btnCs
        btnSN = binding.btnSn
        btnMPC = binding.btnMpc


        // initialise text views
        tvGreeting = binding.tvGreeting
        tvScoreGK = binding.tvScoreGk
        tvScoreCS = binding.tvScoreCs
        tvScoreSN = binding.tvScoreSn
        tvScoreMPC = binding.tvScoreMpc

        // initialise firebase auth
        auth = FirebaseAuth.getInstance()

        // initialise firebase firestore
        firestore = FirebaseFirestore.getInstance()

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

        // General Knowledge button click
        btnGK.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("categoryName", "General Knowledge")
            startActivity(intent)
        }


        // Computer Science button click
        btnCS.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("categoryName", "Computer Science")
            startActivity(intent)
        }

        // Science and Nature button click
        btnSN.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("categoryName", "Science and Nature")
            startActivity(intent)
        }

        // Movies and Pop Culture button click
        btnMPC.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("categoryName", "Movies and Pop Culture")
            startActivity(intent)
        }

        // get the current user id
        val userId = auth.currentUser?.uid

        // get user scores for each category from firestore and update text views
        getMostRecentUserScore(userId.toString(), "General Knowledge") {score ->
            tvScoreGK.text = "Last Score: $score"
        }

        getMostRecentUserScore(userId.toString(), "Computer Science") {score ->
            tvScoreCS.text = "Last Score: $score"
        }

        getMostRecentUserScore(userId.toString(), "Science and Nature") {score ->
            tvScoreSN.text = "Last Score: $score"
        }

        getMostRecentUserScore(userId.toString(), "Movies and Pop Culture") {score ->
            tvScoreMPC.text = "Last Score: $score"
        }

    }

    private fun getMostRecentUserScore(userId: String, categoryName: String, callback: (Int) -> Unit)
    {

        firestore.collection("quiz-user-scores")
            .whereEqualTo("userId", userId)
            .whereEqualTo("categoryName", categoryName)
            .orderBy("timestamp", Query.Direction.DESCENDING)  // Ensure you get the most recent score
            .limit(1)
            .get()

            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot != null && !querySnapshot.isEmpty)
                {
                    // Assuming the score is stored in a field named "score"
                    val score = querySnapshot.documents[0].getLong("score")?.toInt() ?: 0
                    callback(score)

                }
                else
                {
                    Log.d("MainActivity", "No score found for category $categoryName")
                    // No score found
                    callback(0)
                }
            }
    }

}