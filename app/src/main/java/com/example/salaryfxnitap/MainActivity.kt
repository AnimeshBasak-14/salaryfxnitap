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
        databaseReference = FirebaseDatabase.getInstance().reference.child("users")

        mainBinding.progressBar.visibility = View.INVISIBLE

        mainBinding.buttonSignOut.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        mainBinding.buttonGenerateSalarySlip.setOnClickListener {

            // Set up Month RecyclerView
            val monthRecyclerView: RecyclerView = findViewById(R.id.monthsRecyclerView)
            val monthAdapter = MonthAdapter(getMonthList())
            monthRecyclerView.layoutManager = LinearLayoutManager(this)
            monthRecyclerView.adapter = monthAdapter
            val employeeId = mainBinding.editTextEmployeeId.text.toString()
            val selectedMonth = monthAdapter.getSelectedMonth()
            val
            x = 657
            // Show progress bar while processing
            mainBinding.progressBar.visibility = View.VISIBLE

            generateSalarySlip()
            // Check if the user exists in the database
//            checkUserExistence(employeeId, selectedMonth)
        }
    }



    private fun getMonthList(): List<String> {
        return listOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )
    }





    private fun checkUserExistence(employeeId: String, selectedMonth: String) {
        databaseReference.child(employeeId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // User exists in the database, generate salary slip
                    generateSalarySlip()
                } else {
                    // User does not exist, show toast
                    Toast.makeText(this@MainActivity, "Employee not found", Toast.LENGTH_SHORT).show()

                    // Hide progress bar
                    mainBinding.progressBar.visibility = View.INVISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error if any
                Toast.makeText(this@MainActivity, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()

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