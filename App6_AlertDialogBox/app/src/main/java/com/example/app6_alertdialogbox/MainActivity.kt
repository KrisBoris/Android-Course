package com.example.app6_alertdialogbox

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val uninstallButton = findViewById<Button>(R.id.btnUninstall)

        uninstallButton.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("TikTok")
            .setMessage("Do you want to uninstall the app?")
            .setPositiveButton("Yes") { dialog, which ->
                Toast.makeText(this, "App successfully uninstalled", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No, but actually yes") { dialog, which ->
                dialog.dismiss()
            }
        val alertDialog : AlertDialog = builder.create()
        alertDialog.show()
    }
}