package com.example.salaryfxnitap

import com.example.salaryfxnitap.LoginActivity
import com.example.salaryfxnitap.SalarySlip
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salaryfxnitap.adapter.MonthAdapter
import com.example.salaryfxnitap.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val generateButton: Button = findViewById(R.id.buttonGenerateSalarySlip)

        firebaseAuth = FirebaseAuth.getInstance()
//        databaseReference = FirebaseDatabase.getInstance().reference.child("users")

        mainBinding.progressBar.visibility = View.INVISIBLE

        mainBinding.buttonSignOut.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        val employeeId = mainBinding.editTextEmployeeId.text.toString()

        val spinner: Spinner = findViewById(R.id.month_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            this,
            R.array.months_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        mainBinding.buttonGenerateSalarySlip.setOnClickListener {





                generateSalarySlip()

                // Check if the user exists in the database
//                val selectedMonth = spinner.selectedItem.toString() // Get the selected month
//                checkUserExistence(employeeId, selectedMonth)

            }

        }
    }


        private fun checkUserExistence(employeeId: String, selectedMonth: String) {
            databaseReference.child(employeeId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            // User exists in the database, generate salary slip
                            generateSalarySlip()
                        } else {
                            // User does not exist, show toast
                            Toast.makeText(
                                this@MainActivity,
                                "Employee not found",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Hide progress bar
                            mainBinding.progressBar.visibility = View.INVISIBLE
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle database error if any
                        Toast.makeText(
                            this@MainActivity,
                            "Database error: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Hide progress bar
                        mainBinding.progressBar.visibility = View.INVISIBLE
                    }
                })
        }

        private fun generateSalarySlip() {
            // Here you can implement the logic to generate the salary slip
            // For demonstration purposes, let's navigate to the SalarySlip activity
            val intent = Intent(this, SalarySlip::class.java)
            startActivity(intent)

            // Hide progress bar after navigating
            mainBinding.progressBar.visibility = View.INVISIBLE
        }
    }
