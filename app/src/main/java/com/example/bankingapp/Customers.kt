package com.example.bankingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankingapp.adapter.ItemAdapter
import com.example.bankingapp.dbHelper.DBhelper
import com.example.bankingapp.databinding.ActivityCustomersBinding

class Customers : AppCompatActivity(){
    private lateinit var dbHelper:DBhelper
    private lateinit var binding: ActivityCustomersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dbHelper = DBhelper(this)
        viewCustomer()
    }
    private fun viewCustomer(){
        val customersList= dbHelper.getCustomers(this)
        val adapter = ItemAdapter(this,customersList)
        val rv = binding.recycle01
        rv.adapter = adapter
        adapter.setOnItemClickListener(object: ItemAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@Customers,CustomerDetails::class.java)
                intent.putExtra("name",customersList[position].name)
                intent.putExtra("phone",customersList[position].phone)
                intent.putExtra("balance",customersList[position].balance)
                intent.putExtra("accNo",customersList[position].AccountNo)
                startActivity(intent)
                viewCustomer()
            }
        })
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
    }
}