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
                Toast.makeText(
                    applicationContext,
                    "Please enter email and password!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun signInUser(userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
            loginBinding.progressBarlogin.visibility = View.INVISIBLE
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
            } else {
                Toast.makeText(
                    applicationContext,
                    "Authentication failed: ${task.exception?.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
