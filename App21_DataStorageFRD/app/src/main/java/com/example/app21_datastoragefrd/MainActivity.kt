package com.example.app21_datastoragefrd

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app21_datastoragefrd.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSearchVehicleNumber.setOnClickListener {
            if(binding.etSearchVehicleNumber.text.isNotEmpty())
                readData(binding.etSearchVehicleNumber.text.toString())
            else
                Toast.makeText(this@MainActivity, "Enter vehicle's number first", Toast.LENGTH_LONG).show()
        }
    }

    private fun readData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if(it.exists()) {
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRto = it.child("vehicleRTO").value
                Toast.makeText(this@MainActivity, "Result found", Toast.LENGTH_LONG).show()
                binding.etSearchVehicleNumber.text.clear()
                binding.tvReadOwnerName.setText(ownerName.toString())
                binding.tvReadVehicleBrand.setText(vehicleBrand.toString())
                binding.tvReadVehicleRto.setText(vehicleRto.toString())
            }
            else
                Toast.makeText(this@MainActivity, "Vehicle number doesn't exist", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this@MainActivity, "Database error", Toast.LENGTH_LONG).show()
        }
    }
}