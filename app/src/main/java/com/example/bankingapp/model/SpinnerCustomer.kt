package com.example.bankapp.model

data class SpinnerCustomer(val name:String,val phone:String)

object SpinCustomers{
    val phone: CharSequence? = null
    val name: CharSequence? = null
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
    var list : ArrayList<SpinnerCustomer>? = null
        get(){
            if(field != null){
                return field
            }
            field = ArrayList()
            for(i in phoneNumbers.indices){
                val nameID = names[i]
                val phoneID = phoneNumbers[i]
                val customer = SpinnerCustomer(nameID,phoneID)
                field!!.add(customer)
            }
            return field
        }
}