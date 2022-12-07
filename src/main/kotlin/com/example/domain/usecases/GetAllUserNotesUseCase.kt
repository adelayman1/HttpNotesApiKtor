package com.example.domain.usecases

import com.example.domain.models.BaseResponse
import com.example.domain.models.NoteModel
import com.example.domain.repositories.NoteRepository
import com.example.domain.repositories.UserRepository
import java.text.SimpleDateFormat

class GetAllUserNotesUseCase constructor(private var noteRepository: NoteRepository, private var userRepository: UserRepository) {
    suspend operator fun invoke(email: String?,limit:Int?,offset:Int?): BaseResponse<List<NoteModel>> {
        return try {
            if (email.isNullOrEmpty())
                return BaseResponse.ErrorResponse(message = "Authorization not found") as BaseResponse<List<NoteModel>>
            if (!userRepository.isEmailExist(email))
                return BaseResponse.ErrorResponse(message =  "Authorization key is not exist") as BaseResponse<List<NoteModel>>
            if(limit == null || offset == null){
                val getNotesResult = noteRepository.getAllUserNotes(email).sortedBy {
                    val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm")
                    sdf.parse(it.date).time
                }
                return BaseResponse.SuccessResponse(message =  "Notes have got successfully", data = getNotesResult)
            }else{
                val getNotesResult = noteRepository.getUserNotesWithPagination(email,limit,offset).sortedBy {
                    val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm")
                    sdf.parse(it.date).time
                }
                return BaseResponse.SuccessResponse(message =  "Notes have got successfully", data = getNotesResult)
            }

        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message =  "unknown error ${e.message}") as BaseResponse<List<NoteModel>>
        }
    }
}