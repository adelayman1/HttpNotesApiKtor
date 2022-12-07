package com.example.data.repositories

import com.example.data.models.toNoteModel
import com.example.data.source.dao.noteDao.NoteDAO
import com.example.domain.models.NoteModel
import com.example.domain.repositories.NoteRepository

class NoteRepositoryImpl constructor(private var noteDAO: NoteDAO) : NoteRepository {
    override suspend fun addNote(
        email: String,
        title: String,
        description: String,
        subtitle: String,
        date: String,
        image: String?,
        webLink: String?,
        color: Int
    ): NoteModel? {
        val newNote = NoteModel(
            email = email,
            title = title,
            description = description,
            subtitle = subtitle,
            date = date,
            image = image,
            webLink = webLink,
            color = color
        )
        return noteDAO.insertNote(note = newNote)?.let(::toNoteModel)
    }


    override suspend fun deleteNote(noteID: Int): Boolean {
        return noteDAO.deleteNote(noteID)
    }

    override suspend fun getAllUserNotes(email: String): List<NoteModel> {
        return noteDAO.getNotesByUserEmail(email)
    }

    override suspend fun getUserNotesWithPagination(email: String, limit: Int, offset: Int): List<NoteModel> {
        return noteDAO.getNotesWithPaginationByUserEmail(email,limit,offset)
    }

    override suspend fun getNoteDetails(noteID: Int): NoteModel? {
        return noteDAO.getNoteByID(noteID)?.let(::toNoteModel)
    }

    override suspend fun updateNoteDescription(noteID: Int, description: String): NoteModel? {
        val updateNoteResult = noteDAO.updateNoteDescriptionByNoteID(noteID, description)
        return if (updateNoteResult) getNoteDetails(noteID) else null
    }

    override suspend fun updateNoteTitle(noteID: Int, title: String): NoteModel? {
        val updateNoteResult = noteDAO.updateNoteTitleByNoteID(noteID, title)
        return if (updateNoteResult) getNoteDetails(noteID) else null
    }

    override suspend fun updateNoteSubtitle(noteID: Int, subtitle: String): NoteModel? {
        val updateNoteResult = noteDAO.updateNoteSubtitleByNoteID(noteID, subtitle)
        return if (updateNoteResult) getNoteDetails(noteID) else null
    }

    override suspend fun updateNoteImage(noteID: Int, image: String?): NoteModel? {
        val updateNoteResult = noteDAO.updateNoteImageByNoteID(noteID, image)
        return if (updateNoteResult) getNoteDetails(noteID) else null
    }

    override suspend fun updateNoteWebLink(noteID: Int, webLink: String?): NoteModel? {
        val updateNoteResult = noteDAO.updateNoteWeblinkByNoteID(noteID, webLink)
        return if (updateNoteResult) getNoteDetails(noteID) else null
    }

    override suspend fun updateNoteColor(noteId: Int, color: Int): NoteModel? {
        val updateNoteResult = noteDAO.updateNoteColorById(noteId, color)
        return if (updateNoteResult) getNoteDetails(noteId) else null
    }
}