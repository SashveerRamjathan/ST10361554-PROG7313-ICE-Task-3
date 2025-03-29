package com.st10361554.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.st10361554.quizapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    // fields
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    // buttons
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialise buttons & fields
        etEmail = binding.etEmail
        etPassword = binding.etPassword
        btnLogin = binding.btnLogin
        btnRegister = binding.btnRegister

        // initialize firebase auth
        auth = FirebaseAuth.getInstance()

        // login button click
        btnLogin.setOnClickListener {

            // get email and password
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // check if email and password are not empty
            if (email.isNotEmpty() && password.isNotEmpty())
            {
                loginUser(email, password)
            }
            else
            {
                // show error message
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }

        }

        // register button click
        btnRegister.setOnClickListener {

        }


    }

    // method to login user and redirect appropriately
    private fun loginUser(email: String, password: String)
    {
        auth.signInWithEmailAndPassword(email, password)

            .addOnCompleteListener { task ->

                if (task.isSuccessful)
                {
                    // Login is successful
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    // navigate to main activity
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else
                {
                    // Login failed
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()

                    // log the error
                    Log.d("LoginActivity", "Login Failed: ${task.exception?.message}")

                    // navigate back to login activity
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
    }
}