package com.example.salaryfxnitap

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.salaryfxnitap.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

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


        val editTextEmployeeId = findViewById<EditText>(R.id.editTextEmployeeId)

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

        }

//        println(employeeId)
        mainBinding.buttonGenerateSalarySlip.setOnClickListener {

            val selectedMonth = spinner.selectedItem.toString()
            val employeeId = editTextEmployeeId.text.toString().trim()
            generateSalarySlip(employeeId,selectedMonth)

                // Check if the user exists in the database
//                val selectedMonth = spinner.selectedItem.toString() // Get the selected month
//                checkUserExistence(employeeId, selectedMonth)

            }


    }


    private fun generateSalarySlip(employeeId: String, selectedMonth: String) {
        // Here you can implement the logic to generate the salary slip
        // For demonstration purposes, let's navigate to the SalarySlip activity


            Toast.makeText(
                this@MainActivity,
                "Found: ${"$employeeId: $selectedMonth"}",
                Toast.LENGTH_SHORT
            ).show()



        val intent = Intent(this, SalarySlip::class.java).apply {
            putExtra("employeeId", employeeId)
            putExtra("selectedMonth", selectedMonth)
        }
        startActivity(intent)

        // Hide progress bar after navigating
        mainBinding.progressBar.visibility = View.INVISIBLE
    }

}
