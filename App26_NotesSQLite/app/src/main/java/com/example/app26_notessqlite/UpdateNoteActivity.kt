package com.example.app26_notessqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app26_notessqlite.databinding.ActivityUpdateBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

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

        db = NoteDatabaseHelper(this@UpdateNoteActivity)

        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.etUpdateTitle.setText(note.title)
        binding.etUpdateContent.setText(note.content)

        binding.ivUpdateButton.setOnClickListener {
            db.updateNote(Note(noteId, binding.etUpdateTitle.text.toString(), binding.etUpdateContent.text.toString()))
            finish()
            Toast.makeText(this@UpdateNoteActivity, "Changes saved", Toast.LENGTH_SHORT).show()
        }
    }
}