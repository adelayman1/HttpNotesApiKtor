package com.example.domain.usecases

import com.example.domain.models.BaseResponse
import com.example.domain.models.NoteModel
import com.example.domain.repositories.NoteRepository
import com.example.domain.repositories.UserRepository

class UpdateNoteDetailsUseCase constructor(private var noteRepository: NoteRepository, private var userRepository: UserRepository) {
    suspend operator fun invoke(
        email: String?,
        noteID: String?,
        title: String?,
        description: String?,
        subtitle: String?,
        image: String?,
        webLink: String?,
        color:Int?
    ): BaseResponse<NoteModel> {
        return try {
            if (noteID.isNullOrEmpty())
                return BaseResponse.ErrorResponse(message = "note ID is empty") as BaseResponse<NoteModel>
            if (email.isNullOrEmpty())
                return BaseResponse.ErrorResponse(message = "Not Authorized") as BaseResponse<NoteModel>
            if (!userRepository.isEmailExist(email)) (return BaseResponse.ErrorResponse(message = "Authorization key is not exist") as BaseResponse<NoteModel>)
            val getNoteDetailsResult =
                noteRepository.getNoteDetails(noteID.toInt())
                    ?: return BaseResponse.ErrorResponse(message = "note not found") as BaseResponse<NoteModel>
            if (getNoteDetailsResult.email != email) return BaseResponse.ErrorResponse(message = "Not Authorized") as BaseResponse<NoteModel>
            var result: NoteModel? = null
            if (!title.isNullOrEmpty()) {
                val updateNoteTitleResult = noteRepository.updateNoteTitle(noteID.toInt(), title)
                result = updateNoteTitleResult
            }
            if (!description.isNullOrEmpty()) {
                val updateNoteDescriptionResult = noteRepository.updateNoteDescription(noteID.toInt(), description)
                result = updateNoteDescriptionResult
            }
            if (!subtitle.isNullOrEmpty()) {
                val updateNoteCategoryResult = noteRepository.updateNoteSubtitle(noteID.toInt(), subtitle)
                result = updateNoteCategoryResult
            }
            val updateNoteWeblinkResult = noteRepository.updateNoteWebLink(noteID.toInt(), webLink)
            result = updateNoteWeblinkResult

            val updateNoteImageResult = noteRepository.updateNoteImage(noteID.toInt(), image)
            result = updateNoteImageResult
            if (color != null){
                val updateNoteColorResult = noteRepository.updateNoteColor(noteID.toInt(), color)
                result = updateNoteColorResult
            }


            return if (result == null) BaseResponse.ErrorResponse(message = "please fill fields first") as BaseResponse<NoteModel>
            else BaseResponse.SuccessResponse(message = "Note details has updated successfully", data = result)
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "unknown error ${e.message}") as BaseResponse<NoteModel>
        }
    }
}