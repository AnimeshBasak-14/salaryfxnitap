package com.example.salaryfxnitap

import com.example.salaryfxnitap.LoginActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.salaryfxnitap.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    lateinit var forgotPasswordBinding: ActivityForgotPasswordBinding
    val auth =FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotPasswordBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view  = forgotPasswordBinding.root
        setContentView(view)



        forgotPasswordBinding.buttonForgot.setOnClickListener()
        {
            val userEmail = forgotPasswordBinding.editTextForgotEmail.text.toString()
            forgotPasswordBinding.progressBarforgot.visibility = View.INVISIBLE
            auth.sendPasswordResetEmail(userEmail).addOnCompleteListener{task->
                if(task.isSuccessful)
                {
                    Toast.makeText(
                    applicationContext,
                    "The Reset Password is Sent to your Respected Email Id",
                    Toast.LENGTH_SHORT).show()
                    forgotPasswordBinding.progressBarforgot.visibility = View.VISIBLE
                    val intent = Intent(this@ForgotPassword, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(
                        applicationContext,
                        task.exception?.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }
}