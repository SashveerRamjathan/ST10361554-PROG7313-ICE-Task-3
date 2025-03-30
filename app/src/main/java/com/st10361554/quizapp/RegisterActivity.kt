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
import com.st10361554.quizapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    // Fields
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText

    // Buttons
    private lateinit var btnRegister: Button
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialise fields
        etEmail = binding.etEmail
        etPassword = binding.etPassword
        etConfirmPassword = binding.etConfirmPassword

        // Initialise buttons
        btnRegister = binding.btnRegister
        btnLogin = binding.btnLogin

        // Initialise firebase auth
        auth = FirebaseAuth.getInstance()

        // Register button click
        btnRegister.setOnClickListener {

            // get values from fields
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            //check if all fields are filled
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
            {
                // check if password and confirm password match
                if (password == confirmPassword)
                {
                    // register user
                    registerUser(email, password)
                }
                else
                {
                    // show error message
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                // show error message
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Login button click
        btnLogin.setOnClickListener {

            // Navigate to login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }
    }

    // Method to register user and redirect appropriately
    private fun registerUser(email: String, password: String)
    {
        auth.createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful)
                {
                    // Registration is successful
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                    // redirect to login activity for user to login
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                else
                {
                    // Registration failed
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()

                    // log the error
                    Log.d("RegisterActivity", "Registration Failed: ${task.exception?.message}")

                    // redirect back to register activity
                    startActivity(Intent(this, RegisterActivity::class.java))
                    finish()
                }
            }
    }
}