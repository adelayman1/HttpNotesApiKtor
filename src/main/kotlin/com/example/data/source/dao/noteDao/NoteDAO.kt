package com.example.data.source.dao.noteDao

import com.example.domain.models.NoteModel
import org.jetbrains.exposed.sql.ResultRow

interface NoteDAO {
    suspend fun insertNote(note: NoteModel): ResultRow?
    suspend fun getNotesByUserEmail(email: String): List<NoteModel>
    suspend fun getNotesWithPaginationByUserEmail(email: String, limit:Int, offset:Int): List<NoteModel>
    suspend fun getNoteByID(noteID: Int): ResultRow?
    suspend fun updateNoteDescriptionByNoteID(noteID: Int, description: String): Boolean
    suspend fun updateNoteTitleByNoteID(noteID: Int, title: String): Boolean
    suspend fun updateNoteSubtitleByNoteID(noteID: Int, subtitle: String): Boolean
    suspend fun updateNoteImageByNoteID(noteID: Int, image: String?): Boolean
    suspend fun updateNoteWeblinkByNoteID(noteID: Int, weblink: String?): Boolean
    suspend fun deleteNote(noteID: Int): Boolean
    suspend fun updateNoteColorById(noteId: Int, color: Int): Boolean
}