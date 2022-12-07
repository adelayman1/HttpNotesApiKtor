package com.example.data.source.dao.noteDao

import com.example.data.models.Notes
import com.example.data.models.toNoteModel
import com.example.data.source.DatabaseFactory.Companion.dbQuery
import com.example.domain.models.NoteModel
import org.jetbrains.exposed.sql.*
import java.text.SimpleDateFormat
import java.util.*

class NoteDAOImpl : NoteDAO {
    override suspend fun insertNote(note: NoteModel): ResultRow? {
        return dbQuery {
            val insertStatement = Notes.insert { notes ->
                notes[userEmail] = note.email
                notes[title] = note.title
                notes[subtitle] = note.subtitle
                notes[description] = note.description
                notes[date] = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.getDefault()).format(
                    Date()
                )
                notes[image] = note.image
                notes[webLink] = note.webLink
                notes[color] = note.color
            }
            insertStatement.resultedValues?.singleOrNull()
        }
    }

    override suspend fun getNotesByUserEmail(email: String): List<NoteModel> {
        return dbQuery {
            Notes.select { Notes.userEmail eq email}
                .mapNotNull { note -> toNoteModel(note) }
        }
    }

    override suspend fun getNotesWithPaginationByUserEmail(email: String, limit:Int, offset:Int): List<NoteModel> {
        return dbQuery {
            Notes.select { Notes.userEmail eq email}
                .limit(limit,offset.toLong())
                .mapNotNull { note -> toNoteModel(note) }
        }
    }

    override suspend fun getNoteByID(noteID: Int): ResultRow? {
        return dbQuery {
            Notes.select { Notes.noteId eq noteID }
                .singleOrNull()
        }
    }

    override suspend fun updateNoteDescriptionByNoteID(noteID: Int, description: String): Boolean {
        return dbQuery {
            Notes.update({ Notes.noteId eq noteID }) { note ->
                note[this.description] = description
            } > 0
        }
    }

    override suspend fun updateNoteTitleByNoteID(noteID: Int, title: String): Boolean {
        return dbQuery {
            Notes.update({ Notes.noteId eq noteID }) { note ->
                note[Notes.title] = title
            } > 0
        }
    }

    override suspend fun updateNoteSubtitleByNoteID(noteID: Int, subtitle: String): Boolean {
        return dbQuery {
            Notes.update({ Notes.noteId eq noteID }) { note ->
                note[Notes.subtitle] = subtitle
            } > 0
        }
    }

    override suspend fun updateNoteImageByNoteID(noteID: Int, image: String?): Boolean {
        return dbQuery {
            Notes.update({ Notes.noteId eq noteID }) { note ->
                note[Notes.image] = image
            } > 0
        }
    }

    override suspend fun updateNoteWeblinkByNoteID(noteID: Int, weblink: String?): Boolean {
        return dbQuery {
            Notes.update({ Notes.noteId eq noteID }) { note ->
                note[Notes.webLink] = weblink
            } > 0
        }
    }

    override suspend fun deleteNote(noteID: Int): Boolean {
        return dbQuery {
            Notes.deleteWhere { Notes.noteId eq noteID } > 0
        }
    }

    override suspend fun updateNoteColorById(noteId: Int, color: Int): Boolean {
        return dbQuery {
            Notes.update({ Notes.noteId eq noteId }) { note ->
                note[Notes.color] = color
            } > 0
        }
    }
}