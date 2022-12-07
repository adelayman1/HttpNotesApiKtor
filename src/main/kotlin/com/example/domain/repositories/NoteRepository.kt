package com.example.domain.repositories

import com.example.domain.models.NoteModel

interface NoteRepository {
    suspend fun addNote(
        email: String,
        title: String,
        description: String,
        subtitle: String,
        date: String,
        image: String?,
        webLink: String?,
        color:Int
    ): NoteModel?

    suspend fun deleteNote(noteID: Int): Boolean
    suspend fun getAllUserNotes(email: String): List<NoteModel>
    suspend fun getUserNotesWithPagination(email: String, limit: Int, offset: Int): List<NoteModel>
    suspend fun getNoteDetails(noteID: Int): NoteModel?
    suspend fun updateNoteDescription(noteID: Int, description: String): NoteModel?
    suspend fun updateNoteTitle(noteID: Int, title: String): NoteModel?
    suspend fun updateNoteSubtitle(noteID: Int, subtitle: String): NoteModel?
    suspend fun updateNoteImage(noteID: Int, image: String?): NoteModel?
    suspend fun updateNoteWebLink(noteID: Int, webLink: String?): NoteModel?
    suspend fun updateNoteColor(noteId:Int,color: Int): NoteModel?
}