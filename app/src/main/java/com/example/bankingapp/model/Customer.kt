package com.example.bankingapp.model

data class Customer(
    var name:String,
    var phone:Long,
    var balance:Double,
    var email:String,
    var AccountNo:String,
    var ifsc:String)

private val names = arrayListOf<String>(
    "Kavya sita raman",
    "Ram",
    "Khushal",
    "Samarth Shah",
    "Akshay ",
    "Rajat",
    "Suresh Sharma",
    "Kiran Singh",
)
private val phoneNumbers = arrayListOf<String>(
    "0908777632",
    "8456789012",
    "8749984873",
    "2678901234",
    "3890123456",
    "0999987654",
    "5012345678",
    "5647837648"
)