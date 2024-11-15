package com.example.app49_notesapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.app49_notesapp.MainActivity
import com.example.app49_notesapp.R
import com.example.app49_notesapp.databinding.FragmentEditNoteBinding
import com.example.app49_notesapp.fragments.EditNoteFragmentArgs
import com.example.app49_notesapp.model.Note
import com.example.app49_notesapp.viewmodel.NoteViewModel

class EditNoteFragment : Fragment(R.layout.fragment_edit_note), MenuProvider {

    private var editNoteBinding: FragmentEditNoteBinding? = null
    private val binding get() = editNoteBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote: Note

    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.etEditNoteTitle.setText(currentNote.noteTitle)
        binding.etEditNoteContent.setText(currentNote.noteDescription)

        binding.fabEditNote.setOnClickListener {
            val noteTitle = binding.etEditNoteTitle.text.toString().trim()

            if(noteTitle.isNotEmpty()) {
                val noteContent = binding.etEditNoteContent.text.toString().trim()
                val note = Note(currentNote.id, noteTitle, noteContent)

                noteViewModel.updateNote(note)
                it.findNavController().popBackStack(R.id.homeFragment, false)
            }
            else
                Toast.makeText(context, "Please enter title", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete") { _,_ ->
                noteViewModel.deleteNote(currentNote)
                Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.deleteMenu -> {
                deleteNote()
                true
            }
            else -> false
        }
    }
}