package com.example.domain.usecases

import com.example.domain.models.BaseResponse
import com.example.domain.models.NoteModel
import com.example.domain.repositories.NoteRepository
import com.example.domain.repositories.UserRepository
import java.text.SimpleDateFormat

class SearchNoteUseCase constructor(private var noteRepository: NoteRepository, private var userRepository: UserRepository) {
    suspend operator fun invoke(
        email: String?,
        searchWord: String?,
        limit: Int?,
        offset: Int?
    ): BaseResponse<List<NoteModel>> {
        return try {
            if (searchWord.isNullOrEmpty())
                return BaseResponse.ErrorResponse(message = "search word not found") as BaseResponse<List<NoteModel>>

            if (email.isNullOrEmpty())
                return BaseResponse.ErrorResponse(message = "Authorization not found") as BaseResponse<List<NoteModel>>
            if (!userRepository.isEmailExist(email))
                return BaseResponse.ErrorResponse(message = "Authorization key is not exist") as BaseResponse<List<NoteModel>>
            var getNotesResult = noteRepository.getAllUserNotes(email).filter { note ->
                note.title.lowercase().contains(searchWord.lowercase())
                        || note.subtitle.lowercase().contains(searchWord.lowercase())
                        || note.description.lowercase().contains(searchWord.lowercase())
            }.sortedBy {
                val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm")
                sdf.parse(it.date).time
            }
            if (limit!=null&&offset!= null){
                getNotesResult =getNotesResult.windowed(size = limit, step = offset, partialWindows = true).first()
            }
            return BaseResponse.SuccessResponse(message = "Notes have got successfully", data = getNotesResult)
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "unknown error ${e.message}") as BaseResponse<List<NoteModel>>
        }
    }
}