package com.example.app21_datastoragefrdadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app21_datastoragefrdadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.apply {

            btnUpdateData.setOnClickListener {
                if(etUpdateNumber.text.isNotEmpty() &&
                    etUpdateOwner.text.isNotEmpty() &&
                    etUpdateVehicleBrand.text.isNotEmpty() &&
                    etUpdateVehicleRTO.text.isNotEmpty())
                    updateData(etUpdateNumber.text.toString(), etUpdateOwner.text.toString(),
                        etUpdateVehicleBrand.text.toString(), etUpdateVehicleRTO.text.toString())
                else
                    Toast.makeText(this@UpdateActivity, "Fill all the fields first", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateData(vehicleNumber: String, ownerName: String, vehicleBrand: String, vehicleRto: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicleData = mapOf<String, String>("ownerName" to ownerName, "vehicleBrand" to vehicleBrand, "vehicleRTO" to vehicleRto)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.apply {
                etUpdateNumber.text.clear()
                etUpdateOwner.text.clear()
                etUpdateVehicleBrand.text.clear()
                etUpdateVehicleRTO.text.clear()
                Toast.makeText(this@UpdateActivity, "Upload successful", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this@UpdateActivity, "Upload failed", Toast.LENGTH_LONG).show()
        }
    }
}