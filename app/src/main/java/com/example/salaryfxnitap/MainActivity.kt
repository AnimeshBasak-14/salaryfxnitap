package com.example.salaryfxnitap

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private var ename: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val generateButton: Button = findViewById(R.id.buttonGenerateSalarySlip)

        firebaseAuth = FirebaseAuth.getInstance()

        mainBinding.progressBar.visibility = View.INVISIBLE

        mainBinding.buttonSignOut.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        var user = firebaseAuth.currentUser;
        var email = user?.email;

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

        mainBinding.buttonGenerateSalarySlip.setOnClickListener {
            val selectedMonth = spinner.selectedItem.toString()
            val useremail = email.toString().trim()
            generateSalarySlip(useremail,selectedMonth)
            }
    }


    private fun generateSalarySlip(useremail: String, selectedMonth: String) {



        if (selectedMonth.isNotEmpty() && useremail.isNotEmpty() ) {
            mDatabase = FirebaseDatabase
                .getInstance()
                .getReference("17m2SXM94KhBDJ3tdHR3lI4HLOZK6CjHQgzg8Zq_gX9Q")
                .child("Sheet1")
            mDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        val paySlipMonth = childSnapshot.child("PaySlipMonth").getValue(String::class.java)
                        val emailId = childSnapshot.child("EmailId").getValue(String::class.java)
                        ename = childSnapshot.child("EmployeeName").getValue(String::class.java).toString()

                        if (emailId == useremail && paySlipMonth == selectedMonth) {
                            val eid = childSnapshot.child("EID").getValue(Long::class.java)
                            // Hide progress bar after navigating
                            mainBinding.progressBar.visibility = View.INVISIBLE

                            val intent = Intent(this@MainActivity, SalarySlip::class.java).apply {
                                putExtra("eid", eid.toString())
                            }
                            startActivity(intent)
                            return  // Break out of the loop
                        }
                    }

                    Toast.makeText(this@MainActivity,
                        "Wrong selected month for $ename",
                        Toast.LENGTH_SHORT).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                    Log.e(ContentValues.TAG, "Database error occurred: ${error.message}")
                    // You can also show a Toast message to the user
                    Toast.makeText(
                        this@MainActivity,
                        "Database error occurred: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            )
        }
        else {
            // Handle case when employeeId or selectedMonth is empty or invalid
            Toast.makeText(this@MainActivity,
                "Empty selected month",
                Toast.LENGTH_SHORT).show()
        }
    }
}