package com.example.salaryfxnitap

import com.example.salaryfxnitap.LoginActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.salaryfxnitap.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {
    lateinit var signinBinding: ActivitySigninBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signinBinding = ActivitySigninBinding.inflate(layoutInflater)
        val view = signinBinding.root
        setContentView(view)
        signinBinding.buttonsignin.setOnClickListener()
        {
            val email = signinBinding.editTextForgotEmail.text.toString()
            val password = signinBinding.editTextsignupPassword.text.toString()
            signupwithFirebase(email, password)

        }
    }

    fun signupwithFirebase(email: String, password: String) {

        signinBinding.buttonsignin.isClickable = false;
        signinBinding.progressBarlogin.visibility = View.INVISIBLE
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "Your Account has been created",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
                signinBinding.progressBarlogin.visibility = View.VISIBLE
                signinBinding.buttonsignin.isClickable = true;
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(
                    applicationContext,
                    task.exception?.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}











