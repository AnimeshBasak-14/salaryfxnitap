package com.example.salaryfxnitap.model




data class SalaryDetails(
    val basicPay: Double = 0.0,
    val branch: String = "",
    val daArrear: Double = 0.0,
    val employeeId: String = "",
    val employeeName: String = "",
    val hraRecovery: Double = 0.0,
    val ia: Double = 0.0,
    val interestOfComputerAdv: Double = 0.0,
    val lic: Double = 0.0,
    val mca: Double = 0.0,
    val nectScl: Double = 0.0,
    val others: Double = 0.0,
    val payRecovery: Double = 0.0,
    val paySlipMonth: String = "",
    val taAndDaAdjustment: Double = 0.0,
    val taArrear: Double = 0.0,
    val deductionOthers: Double = 0.0,
    val grf: Double = 0.0
) {
    // Ensure that the class has a no-argument constructor
    constructor() : this(0.0, "", 0.0, "", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "", 0.0, 0.0, 0.0, 0.0)
}


