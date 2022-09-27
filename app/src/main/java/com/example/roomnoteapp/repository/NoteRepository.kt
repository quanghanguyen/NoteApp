package com.example.roomnoteapp.repository

import androidx.lifecycle.LiveData
import com.example.roomnoteapp.data.NoteDao
import com.example.roomnoteapp.model.Notes

class NoteRepository (private val noteDao : NoteDao) {

    val readAllNotes : LiveData<List<Notes>> = noteDao.getAllNotes()

    suspend fun addNote(notes: Notes) {
        noteDao.insertNotes(notes)
    }
}