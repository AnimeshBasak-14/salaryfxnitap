package com.example.salaryfxnitap


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salaryfxnitap.adapter.MyAdapter
import com.example.salaryfxnitap.model.SalaryModel
import com.google.firebase.database.*
import com.google.firebase.database.R


//class SalarySlip : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var salaryList: Array<SalaryModel>
//    private lateinit var adapter: MyAdapter
//    private lateinit var mDatabase: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_salary_slip)
//        val employeeId = intent.getStringExtra("employeeId")
//        val selectedMonth = intent.getStringExtra("selectedMonth")
//
//        // Initialize Firebase Database
//        mDatabase = FirebaseDatabase.getInstance().reference
//
//        // Fetch salary data based on employee ID and month
//        if (selectedMonth != null && employeeId != null) {
////            Toast.makeText(
////                            this@SalarySlip,
////                            "Found: ${employeeId +" "+ selectedMonth}",
////                            Toast.LENGTH_SHORT
////                        ).show()
//            fetchSalaryData(employeeId.toLong(), selectedMonth)
//        }
////        else{
////
////            if (employeeId != null) {
////                Toast.makeText(
////                    this@SalarySlip,
////                    "Not Found: ${if (employeeId.isEmpty()) "Employee ID is empty" else "Employee ID: $employeeId, Selected Month: $selectedMonth"}",
////                    Toast.LENGTH_SHORT
////                ).show()
////            }
////        }
//    }
//
//    private fun fetchSalaryData(employeeId: Long, selectedMonth: String) {
//        mDatabase = FirebaseDatabase
//            .getInstance()
//            .getReference("17m2SXM94KhBDJ3tdHR3lI4HLOZK6CjHQgzg8Zq_gX9Q")
//            .child("Sheet1")
//            .child(employeeId.toString()) // Use employeeId here to dynamically construct the path
//        mDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val itemCount = snapshot.childrenCount.toInt()
//                salaryList = arrayOfNulls<SalaryModel>(itemCount + 0) as Array<SalaryModel>
//                println(itemCount)
//                var index = 0
//
//                if (snapshot.exists()) {
//
//                    // Employee data found, check if the month matches
//                    val employeeMonth = snapshot.child("PaySlipMonth").getValue(String::class.java)
//                    if (employeeMonth == selectedMonth) {
//                        salaryList[index++] = SalaryModel(
//                            " Employee ID: " + snapshot.child("EID")
//                                .getValue(Long::class.java)
//                        )
//
//                        salaryList[index++] = SalaryModel(
//                            " Employee Name: " + snapshot.child("EmployeeName")
//                                .getValue(String::class.java)
//                        )
//                        salaryList[index++] = SalaryModel(
//                            " Pay Slip Month: " + snapshot.child("PaySlipMonth")
//                                .getValue(String::class.java)
//                        )
//                        salaryList[index++] = SalaryModel(
//                            " Email-id: " + snapshot.child("EmailId")
//                                .getValue(String::class.java)
//                        )
//                        salaryList[index++] = SalaryModel(
//                            " Branch: " + snapshot.child("Branch")
//                                .getValue(String::class.java)
//                        )
//
//                        // Basic Pay
//                        salaryList[index++] =
//                            SalaryModel(" Basic Pay: " + snapshot.child("BasicPay").getValue())
//
//                        // Grade Pay
//                        salaryList[index++] =
//                            SalaryModel(" Grade Pay: " + snapshot.child("GradePay").getValue())
//
//                        // Special Pay
//                        salaryList[index++] =
//                            SalaryModel(" Special Pay: " + snapshot.child("SpecialPay").getValue())
//                        // DA
//                        salaryList[index++] =
//                            SalaryModel(
//                                " Dearness Allowance: " + snapshot.child("DearnessAllowance")
//                                    .getValue()
//                            )
//
//                        // SCA
//                        salaryList[index++] =
//                            SalaryModel(" SCA: " + snapshot.child("SCA").getValue())
//
//
//                        // SDA
//                        salaryList[index++] =
//                            SalaryModel(" SDA: " + snapshot.child("SDA").getValue())
//
//                        // Transport Allowance
//                        salaryList[index++] =
//                            SalaryModel(
//                                " Transport Allowance: " + snapshot.child("TransportAllowance")
//                                    .getValue()
//                            )
//
//
//                        // HRA
//                        salaryList[index++] =
//                            SalaryModel(" HRA: " + snapshot.child("HRA").getValue())
//
//                        // Deputation
//                        salaryList[index++] =
//                            SalaryModel(" Deputation: " + snapshot.child("Deputation").getValue())
//
//                        // DA(Arrear)
//                        salaryList[index++] =
//                            SalaryModel(" DA(Arrear): " + snapshot.child("DA(Arrear)").getValue())
//                        // TA(Arrear)
//                        salaryList[index++] =
//                            SalaryModel(" TA(Arrear): " + snapshot.child("TA(Arrear)").getValue())
//                        // IncomeTax
//                        salaryList[index++] =
//                            SalaryModel(" Income Tax: " + snapshot.child("IncomeTax").getValue())
//                        // NPS
//                        salaryList[index++] =
//                            SalaryModel(" NPS: " + snapshot.child("NPS").getValue())
//                        // PMCaresFund
//                        salaryList[index++] =
//                            SalaryModel(
//                                " PM Cares Fund: " + snapshot.child("PMCaresFund").getValue()
//                            )
//                        // GSLIScheme
//                        salaryList[index++] =
//                            SalaryModel(" GSLI Scheme: " + snapshot.child("GSLIScheme").getValue())
//                        // DeductionTA
//                        salaryList[index++] =
//                            SalaryModel(
//                                " Deduction TA: " + snapshot.child("DeductionTA").getValue()
//                            )
//                        // Busfare/HireCharges
//                        salaryList[index++] =
//                            SalaryModel(
//                                " Busfare/Hire Charges: " + snapshot.child("BusfareHireCharges")
//                                    .getValue()
//                            )
//                        // LTC/TA/Medical
//                        salaryList[index++] =
//                            SalaryModel(
//                                " LTC/TA/Medical: " + snapshot.child("LTCTAMedical").getValue()
//                            )
//                        // LicenceFee
//                        salaryList[index++] =
//                            SalaryModel(" Licence Fee: " + snapshot.child("LicenceFee").getValue())
//                        // QuarterFee
//                        salaryList[index++] =
//                            SalaryModel(" Quarter Fee: " + snapshot.child("QuarterFee").getValue())
//                        // PayRecovery
//                        salaryList[index++] =
//                            SalaryModel(
//                                " Pay Recovery: " + snapshot.child("PayRecovery").getValue()
//                            )
//                        // TotalPayable
//                        salaryList[index++] =
//                            SalaryModel(
//                                " Total Payable: " + snapshot.child("TotalPayable").getValue()
//                            )
//                        // TotalDeduction
//                        salaryList[index++] =
//                            SalaryModel(
//                                " Total Deduction: " + snapshot.child("TotalDeduction").getValue()
//                            )
//                        // NetPayable
//                        salaryList[index++] =
//                            SalaryModel(" Net Payable: " + snapshot.child("NetPayable").getValue())
//
//
//
//                        adapter = MyAdapter(salaryList)
//                        recyclerView.adapter = adapter
//
//                    }
//
//                } else {
//                    // Month does not match
//                    Log.e("FirebaseError", "Employee found, but month does not match.")
//                }
//
//            }
//
//
//
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle database error
//                Log.e(TAG, "Database error occurred: ${error.message}")
//                // You can also show a Toast message to the user
//
//                Toast.makeText(
//                    this@SalarySlip,
//                    "Employee Not Found!: ${error.message}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
//    }
//}
//

class SalarySlip : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var salaryList: Array<SalaryModel>
    private lateinit var adapter: MyAdapter
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salary_slip)


        fetchSalaryData()
    }

    private fun fetchSalaryData() {

        mDatabase = FirebaseDatabase
            .getInstance()
            .getReference("17m2SXM94KhBDJ3tdHR3lI4HLOZK6CjHQgzg8Zq_gX9Q")
            .child("Sheet1")
            .child("3")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemCount = snapshot.childrenCount.toInt()
                salaryList = arrayOfNulls<SalaryModel>(itemCount + 0) as Array<SalaryModel>
                println(itemCount)
                var index = 0


                if (snapshot.child("EmployeeName").getValue(String::class.java) == "Rocky" &&
                    snapshot.child("PaySlipMonth").getValue(String::class.java) == "June" ) {
                    // Fetching Employee Name, Pay Slip Month, and Email-id as the first three items

                    salaryList[index++] = SalaryModel(
                        " Employee ID: " + snapshot.child("EID")
                            .getValue(Long::class.java)
                    )

                    salaryList[index++] = SalaryModel(
                        " Employee Name: " + snapshot.child("EmployeeName")
                            .getValue(String::class.java)
                    )
                    salaryList[index++] = SalaryModel(
                        " Pay Slip Month: " + snapshot.child("PaySlipMonth")
                            .getValue(String::class.java)
                    )
                    salaryList[index++] = SalaryModel(
                        " Email-id: " + snapshot.child("EmailId")
                            .getValue(String::class.java)
                    )
                    salaryList[index++] = SalaryModel(
                        " Branch: " + snapshot.child("Branch")
                            .getValue(String::class.java)
                    )

                    // Basic Pay
                    salaryList[index++] =
                        SalaryModel(" Basic Pay: " + snapshot.child("BasicPay").getValue())

                    // Grade Pay
                    salaryList[index++] =
                        SalaryModel(" Grade Pay: " + snapshot.child("GradePay").getValue())

                    // Special Pay
                    salaryList[index++] =
                        SalaryModel(" Special Pay: " + snapshot.child("SpecialPay").getValue())
                    // DA
                    salaryList[index++] =
                        SalaryModel(" Dearness Allowance: " + snapshot.child("DearnessAllowance").getValue())

                    // SCA
                    salaryList[index++] =
                        SalaryModel(" SCA: " + snapshot.child("SCA").getValue())


                    // SDA
                    salaryList[index++] =
                        SalaryModel(" SDA: " + snapshot.child("SDA").getValue())

                    // Transport Allowance
                    salaryList[index++] =
                        SalaryModel(" Transport Allowance: " + snapshot.child("TransportAllowance").getValue())


                    // HRA
                    salaryList[index++] =
                        SalaryModel(" HRA: " + snapshot.child("HRA").getValue())

                    // Deputation
                    salaryList[index++] =
                        SalaryModel(" Deputation: " + snapshot.child("Deputation").getValue())

                    // DA(Arrear)
                    salaryList[index++] =
                        SalaryModel(" DA(Arrear): " + snapshot.child("DA(Arrear)").getValue())
                    // TA(Arrear)
                    salaryList[index++] =
                        SalaryModel(" TA(Arrear): " + snapshot.child("TA(Arrear)").getValue())
                    // IncomeTax
                    salaryList[index++] =
                        SalaryModel(" Income Tax: " + snapshot.child("IncomeTax").getValue())
                    // NPS
                    salaryList[index++] =
                        SalaryModel(" NPS: " + snapshot.child("NPS").getValue())
                    // PMCaresFund
                    salaryList[index++] =
                        SalaryModel(" PM Cares Fund: " + snapshot.child("PMCaresFund").getValue())
                    // GSLIScheme
                    salaryList[index++] =
                        SalaryModel(" GSLI Scheme: " + snapshot.child("GSLIScheme").getValue())
                    // DeductionTA
                    salaryList[index++] =
                        SalaryModel(" Deduction TA: " + snapshot.child("DeductionTA").getValue())
                    // Busfare/HireCharges
                    salaryList[index++] =
                        SalaryModel(" Busfare/Hire Charges: " + snapshot.child("BusfareHireCharges").getValue())
                    // LTC/TA/Medical
                    salaryList[index++] =
                        SalaryModel(" LTC/TA/Medical: " + snapshot.child("LTCTAMedical").getValue())
                    // LicenceFee
                    salaryList[index++] =
                        SalaryModel(" Licence Fee: " + snapshot.child("LicenceFee").getValue())
                    // QuarterFee
                    salaryList[index++] =
                        SalaryModel(" Quarter Fee: " + snapshot.child("QuarterFee").getValue())
                    // PayRecovery
                    salaryList[index++] =
                        SalaryModel(" Pay Recovery: " + snapshot.child("PayRecovery").getValue())
                    // TotalPayable
                    salaryList[index++] =
                        SalaryModel(" Total Payable: " + snapshot.child("TotalPayable").getValue())
                    // TotalDeduction
                    salaryList[index++] =
                        SalaryModel(" Total Deduction: " + snapshot.child("TotalDeduction").getValue())
                    // NetPayable
                    salaryList[index++] =
                        SalaryModel(" Net Payable: " + snapshot.child("NetPayable").getValue())



                    adapter = MyAdapter(salaryList)
                    recyclerView.adapter = adapter

                }

                else {
//                     Handle the case when the required data is not present
                    Log.e("FirebaseError", "Required data is missing in the database.")

                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e(TAG, "Database error occurred: ${error.message}")
                // You can also show a Toast message to the user
                Toast.makeText(this@SalarySlip, "Database error occurred: ${error.message}", Toast.LENGTH_SHORT).show()
            }

        })

        salaryList = arrayOf(
            SalaryModel(" EmployeeID: "),
            SalaryModel(" Employee Name: "),
            SalaryModel(" Pay Slip Month:"),
            SalaryModel(" Email-id:"),
            SalaryModel(" Branch:"),
            SalaryModel(" Basic Pay:"),
            SalaryModel(" Grade Pay:"),
            SalaryModel(" Special Pay:"),
            SalaryModel(" Dearness Allowance:"),
            SalaryModel(" SCA:"),
            SalaryModel(" SDA:"),
            SalaryModel(" Transport Allowance:"),
            SalaryModel(" HRA:"),
            SalaryModel(" Deputation:"),
            SalaryModel(" DA(Arrear):"),
            SalaryModel(" TA(Arrear):"),
            SalaryModel(" Income Tax:"),
            SalaryModel(" NPS:"),
            SalaryModel(" PM Cares Fund:"),
            SalaryModel(" GSLI Scheme:"),
            SalaryModel(" Deduction TA:"),
            SalaryModel(" Busfare/Hire Charges:"),
            SalaryModel(" LTC/TA/Medical :"),
            SalaryModel(" Licence Fee:"),
            SalaryModel(" Quarter Fee:"),
            SalaryModel(" Pay Recovery:"),
            SalaryModel(" Total Payable:"),
            SalaryModel(" Total Deduction:"),
            SalaryModel(" Net Payable:")
        )

        // Adapter
        recyclerView = findViewById(R.id.recyclerview_item)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(salaryList)
        recyclerView.adapter = adapter

    }

}


