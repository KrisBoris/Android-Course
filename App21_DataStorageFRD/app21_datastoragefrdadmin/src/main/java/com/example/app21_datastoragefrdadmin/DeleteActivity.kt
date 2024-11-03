package com.example.app21_datastoragefrdadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app21_datastoragefrdadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.btnDeleteData.setOnClickListener {
            if(binding.etDeleteVehicleNumber.text.isNotEmpty())
                deleteData(binding.etDeleteVehicleNumber.text.toString())
            else
                Toast.makeText(this@DeleteActivity, "Enter vehicle's number first", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.etDeleteVehicleNumber.text.clear()
            Toast.makeText(this@DeleteActivity, "Deleted successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this@DeleteActivity, "Delete failed", Toast.LENGTH_LONG).show()
        }
    }
}