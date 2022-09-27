package com.example.roomnoteapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomnoteapp.R
import com.example.roomnoteapp.databinding.FragmentCreateNoteBinding
import com.example.roomnoteapp.model.Notes
import com.example.roomnoteapp.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : Fragment() {

    private lateinit var createNoteBinding: FragmentCreateNoteBinding
    private val createNoteViewModel : NoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate()
        back()
        initEvents()
        initObserve()
    }

    private fun initEvents() {
        addNote()
    }

    private fun addNote() {
        createNoteBinding.imgDone.setOnClickListener {
            val title = createNoteBinding.etNoteTitle.text.toString()
            val subTitle = createNoteBinding.etNoteSubTitle.text.toString()
            val noteText = createNoteBinding.etNoteDesc.text.toString()

            if (inputCheck(title, subTitle, noteText)) {
                val note = Notes(0, title, subTitle, createNoteBinding.tvDateTime.text.toString(), noteText)
                createNoteViewModel.addNote(note)
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck(title: String, subTitle: String, noteText: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(subTitle) || TextUtils.isEmpty(noteText))
    }

    private fun initObserve() {
        //
    }


    private fun back() {
        createNoteBinding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDate() {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val currentDate = sdf.format(Date())
        createNoteBinding.tvDateTime.text = currentDate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createNoteBinding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return createNoteBinding.root
    }
}