package com.example.roomnoteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomnoteapp.R
import com.example.roomnoteapp.adapter.NoteAdapter
import com.example.roomnoteapp.databinding.FragmentHomeBinding
import com.example.roomnoteapp.viewmodel.NoteViewModel

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var noteAdapter : NoteAdapter
    private val homeViewModel : NoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
        initList()
        initObserve()
    }

    private fun initObserve() {
        homeViewModel.readAllNotes.observe(viewLifecycleOwner) {result ->
            noteAdapter.addNewNotes(result)
        }
    }

    private fun initList() {
        homeBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            noteAdapter = NoteAdapter(arrayListOf())
            adapter = noteAdapter
        }
    }

    private fun initEvents() {
        addNotes()
    }

    private fun addNotes() {
        homeBinding.fabBtnCreateNote.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }
}