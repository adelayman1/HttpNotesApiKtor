package com.example.domain.usecases

import com.example.domain.models.BaseResponse
import com.example.domain.repositories.NoteRepository
import com.example.domain.repositories.UserRepository

class DeleteNoteUseCase constructor(private var noteRepository: NoteRepository,private var userRepository: UserRepository) {
    suspend operator fun invoke(email: String?, noteID: String?): BaseResponse<Any> {
        return try {
            if (noteID.isNullOrEmpty())
                return BaseResponse.ErrorResponse(message =  "note ID is empty")
            if (email.isNullOrEmpty())
                return BaseResponse.ErrorResponse(message =  "Not Authorized")
            if (!userRepository.isEmailExist(email))
                return BaseResponse.ErrorResponse(message =  "Authorization key is not exist")
            val getNoteDetailsResult = noteRepository.getNoteDetails(noteID.toInt())
                ?: return BaseResponse.ErrorResponse(message =  "Note not found")
            if (getNoteDetailsResult.email != email)
                return BaseResponse.ErrorResponse(message = "Not Authorized")
            val deleteNoteResult = noteRepository.deleteNote(noteID.toInt())
            return if (deleteNoteResult)
                BaseResponse.SuccessResponse(message =  "Note has deleted successfully")
            else
                BaseResponse.ErrorResponse(message = "unknown error!")
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message =  "unknown error ${e.message}")
        }
    }
}