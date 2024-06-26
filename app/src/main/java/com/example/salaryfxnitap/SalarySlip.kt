package com.example.salaryfxnitap


//import android.R
import android.Manifest
import android.R.attr.name
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salaryfxnitap.adapter.MyAdapter
import com.example.salaryfxnitap.model.SalaryModel
import com.google.firebase.database.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class SalarySlip : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var salaryList: Array<SalaryModel>
    private lateinit var adapter: MyAdapter
    private lateinit var mDatabase: DatabaseReference
    private val REQUEST_CODE = 1232

    private lateinit var btnPDF: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salary_slip)

        askPermissions()

//        btnPDF = findViewById(R.id.btnPDF)
//        btnPDF.setOnClickListener {
//            convertPdf() }

        val eid = intent.getStringExtra("eid").toString()


            mDatabase = FirebaseDatabase
                .getInstance()
                .getReference("17m2SXM94KhBDJ3tdHR3lI4HLOZK6CjHQgzg8Zq_gX9Q")
                .child("Sheet1")
                .child(eid)

            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val itemCount = snapshot.childrenCount.toInt()

                    salaryList =
                        arrayOfNulls<SalaryModel>(itemCount ) as Array<SalaryModel>
                    var index = 0

                            // Fetching Employee Name, Pay Slip Month, and Email-id as the first three items
                            salaryList[index++] = SalaryModel(
                                " Employee ID: " + snapshot
                                    .child("EID")
                                    .getValue(Long::class.java)
                            )

                            salaryList[index++] = SalaryModel(
                                " Employee Name: " + snapshot
                                    .child("EmployeeName")
                                    .getValue(String::class.java)
                            )
                            salaryList[index++] = SalaryModel(
                                " Pay Slip Month: " + snapshot
                                    .child("PaySlipMonth")
                                    .getValue(String::class.java)
                            )
                            salaryList[index++] = SalaryModel(
                                " Email-id: " + snapshot
                                    .child("EmailId")
                                    .getValue(String::class.java)
                            )


                            salaryList[index++] = SalaryModel(
                                " Branch: " + snapshot
                                    .child("Branch")
                                    .getValue(String::class.java)
                            )

                            // Basic Pay
                            salaryList[index++] =
                                SalaryModel(
                                    " Basic Pay: " + snapshot
                                        .child("BasicPay")
                                        .getValue()
                                )

                            // Grade Pay
                            salaryList[index++] =
                                SalaryModel(
                                    " Grade Pay: " + snapshot
                                        .child("GradePay")
                                        .getValue()
                                )

                            // Special Pay
                            salaryList[index++] =
                                SalaryModel(
                                    " Special Pay: " + snapshot
                                        .child("SpecialPay")
                                        .getValue()
                                )
                            // DA
                            salaryList[index++] =
                                SalaryModel(
                                    " Dearness Allowance: " + snapshot
                                        .child("DearnessAllowance")
                                        .getValue()
                                )

                            // SCA
                            salaryList[index++] =
                                SalaryModel(" SCA: " + snapshot
                                    .child("SCA")
                                    .getValue())


                            // SDA
                            salaryList[index++] =
                                SalaryModel(" SDA: " + snapshot
                                    .child("SDA")
                                    .getValue())

                            // Transport Allowance
                            salaryList[index++] =
                                SalaryModel(
                                    " Transport Allowance: " + snapshot
                                        .child("TransportAllowance")
                                        .getValue()
                                )


                            // HRA
                            salaryList[index++] =
                                SalaryModel(" HRA: " + snapshot
                                    .child("HRA")
                                    .getValue())

                            // Deputation
                            salaryList[index++] =
                                SalaryModel(
                                    " Deputation: " + snapshot
                                        .child("Deputation")
                                        .getValue()
                                )

                            // DA(Arrear)
                            salaryList[index++] =
                                SalaryModel(
                                    " DA(Arrear): " + snapshot
                                        .child("DA(Arrear)")
                                        .getValue()
                                )
                            // TA(Arrear)
                            salaryList[index++] =
                                SalaryModel(
                                    " TA(Arrear): " + snapshot
                                        .child("TA(Arrear)")
                                        .getValue()
                                )
                            // IncomeTax
                            salaryList[index++] =
                                SalaryModel(
                                    " Income Tax: " + snapshot
                                        .child("IncomeTax")
                                        .getValue()
                                )
                            // NPS
                            salaryList[index++] =
                                SalaryModel(" NPS: " + snapshot
                                    .child("NPS")
                                    .getValue())
                            // PMCaresFund
                            salaryList[index++] =
                                SalaryModel(
                                    " PM Cares Fund: " + snapshot
                                        .child("PMCaresFund")
                                        .getValue()
                                )
                            // GSLIScheme
                            salaryList[index++] =
                                SalaryModel(
                                    " GSLI Scheme: " + snapshot
                                        .child("GSLIScheme")
                                        .getValue()
                                )
                            // DeductionTA
                            salaryList[index++] =
                                SalaryModel(
                                    " Deduction TA: " + snapshot
                                        .child("DeductionTA")
                                        .getValue()
                                )
                            // Busfare/HireCharges
                            salaryList[index++] =
                                SalaryModel(
                                    " Busfare/Hire Charges: " + snapshot
                                        .child("BusfareHireCharges")
                                        .getValue()
                                )
                            // LTC/TA/Medical
                            salaryList[index++] =
                                SalaryModel(
                                    " LTC/TA/Medical: " + snapshot
                                        .child("LTCTAMedical")
                                        .getValue()
                                )
                            // LicenceFee
                            salaryList[index++] =
                                SalaryModel(
                                    " Licence Fee: " + snapshot
                                        .child("LicenceFee")
                                        .getValue()
                                )
                            // QuarterFee
                            salaryList[index++] =
                                SalaryModel(
                                    " Quarter Fee: " + snapshot
                                        .child("QuarterFee")
                                        .getValue()
                                )
                            // PayRecovery
                            salaryList[index++] =
                                SalaryModel(
                                    " Pay Recovery: " + snapshot
                                        .child("PayRecovery")
                                        .getValue()
                                )
                            // TotalPayable
                            salaryList[index++] =
                                SalaryModel(
                                    " Total Payable: " + snapshot
                                        .child("TotalPayable")
                                        .getValue()
                                )
                            // TotalDeduction
                            salaryList[index++] =
                                SalaryModel(
                                    " Total Deduction: " + snapshot
                                        .child("TotalDeduction")
                                        .getValue()
                                )
                            // NetPayable
                            salaryList[index] =
                                SalaryModel(
                                    " Net Payable: " + snapshot
                                        .child("NetPayable")
                                        .getValue()
                                )



                    adapter = MyAdapter(salaryList)
                    recyclerView.adapter = adapter
                }
                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                    Log.e(TAG, "Database error occurred: ${error.message}")
                    // You can also show a Toast message to the user
                    Toast.makeText(
                        this@SalarySlip,
                        "Database error occurred: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            )

//        else {
//            // Handle case when employeeId or selectedMonth is empty or invalid
//            Toast.makeText(this@SalarySlip, "Invalid employee ID or selected month", Toast.LENGTH_SHORT).show()
//        }

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
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(salaryList)
        recyclerView.adapter = adapter

    }
    private fun askPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
    }



    private fun convertPdf() {

        val doc = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(1080, 1920, 1).create()
        val page = doc.startPage(pageInfo)

        val canvas = page.canvas

        val paint = Paint()
        paint.color = Color.RED
        paint.textSize = 42f
        val text = "HEllo WOrld"


        val x = 100
        val y = 100
        canvas.drawText(text, x.toFloat(), y.toFloat(),paint)
        doc.finishPage(page)
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val fileName ="example.pdf"
        val file = File(downloadDir, fileName)
        try {
            val fos = FileOutputStream(file)
            doc.writeTo(fos)
            doc.close()
            fos.close()
        } catch (e: FileNotFoundException) {
            Log.d("my log", "Error while writing $e")
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

}


