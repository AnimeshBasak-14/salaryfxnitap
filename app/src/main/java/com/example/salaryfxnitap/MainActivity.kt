package com.example.salaryfxnitap

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
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


        val employeeId = mainBinding.editTextEmployeeId.text.toString().toLong()

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
            val selectedMonth = spinner.selectedItem.toString()
        mainBinding.buttonGenerateSalarySlip.setOnClickListener {





                generateSalarySlip(employeeId,selectedMonth)

                // Check if the user exists in the database
//                val selectedMonth = spinner.selectedItem.toString() // Get the selected month
//                checkUserExistence(employeeId, selectedMonth)

            }

        }
    }


        private fun checkUserExistence(employeeId: String, selectedMonth: String) {
            mDatabase = FirebaseDatabase
                .getInstance()
                .getReference("17m2SXM94KhBDJ3tdHR3lI4HLOZK6CjHQgzg8Zq_gX9Q")
                .child("Sheet1")

            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.child("EID").getValue(Long::class.java) == employeeId.toLong() &&
                            snapshot.child("PaySlipMonth").getValue(String::class.java) == selectedMonth  ) {
                            // User exists in the database, generate salary slip
                            generateSalarySlip(employeeId.toLong(), selectedMonth)
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

    private fun generateSalarySlip(employeeId: Long, selectedMonth: String) {
        // Here you can implement the logic to generate the salary slip
        // For demonstration purposes, let's navigate to the SalarySlip activity
        val intent = Intent(this, SalarySlip::class.java).apply {
            putExtra("employeeId", employeeId)
            putExtra("selectedMonth", selectedMonth)
        }
        startActivity(intent)

        // Hide progress bar after navigating
        mainBinding.progressBar.visibility = View.INVISIBLE
    }

}
