package com.example.app49_notesapp.fragments

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
import com.example.app49_notesapp.MainActivity
import com.example.app49_notesapp.R
import com.example.app49_notesapp.databinding.FragmentAddNoteBinding
import com.example.app49_notesapp.model.Note
import com.example.app49_notesapp.viewmodel.NoteViewModel

class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    private var addNoteBinding: FragmentAddNoteBinding? = null
    private val binding get() = addNoteBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.etAddNoteTitle.text.toString().trim()

        if(noteTitle.isNotEmpty()) {
            val noteContent = binding.etAddNoteContent.text.toString().trim()
            val note = Note(0, noteTitle, noteContent)

            noteViewModel.insertNote(note)

            Toast.makeText(view.context, "Note saved", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment, false)
        }
        else
            Toast.makeText(addNoteView.context, "Please enter title", Toast.LENGTH_LONG).show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.saveMenu ->  {
                saveNote(addNoteView)
                true
            }
            else -> false
        }
    }
}