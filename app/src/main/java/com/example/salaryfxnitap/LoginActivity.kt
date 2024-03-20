package com.example.salaryfxnitap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.salaryfxnitap.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        auth = FirebaseAuth.getInstance()




        loginBinding.buttonsignin.setOnClickListener {
            val userEmail = loginBinding.editTextLoginEmail.text.toString()
            val userPassword = loginBinding.editTextLoginPassword.text.toString()




            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
                loginBinding.progressBarlogin.visibility = View.VISIBLE
                signInUser(userEmail, userPassword)
            } else {
                if (userPassword.isEmpty() && userEmail.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter your email and password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //        prevents null entries for pass
                else if (userPassword.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter your password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // prevents null entries for email
                else if (userEmail.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter your email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
        loginBinding.textViewforgetpassword.setOnClickListener {
            val intent = Intent(
                this@LoginActivity,
                ForgotPassword::class.java)
                .apply {
            }
            startActivity(intent)
        }
    }

    private fun signInUser(userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
            loginBinding
                .progressBarlogin
                .visibility = View.INVISIBLE
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "Welcome to SALARYFXNITAP",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", userEmail)
                startActivity(intent)
                finish()
            } // Sign in failed
            val errorMessage = task.exception?.localizedMessage ?: "Unknown error"
            when {
                errorMessage.contains("password is invalid", ignoreCase = true) -> {
                    // Password is incorrect
                    Toast.makeText(
                        applicationContext,
                        "Incorrect password. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                errorMessage.contains("user record does not exist", ignoreCase = true) -> {
                    // Email is not registered
                    Toast.makeText(
                        applicationContext,
                        "Email is not registered. Please sign up.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    // Other authentication failures
                    Toast.makeText(
                        applicationContext,
                        "Authentication failed: $errorMessage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
