package com.example.domain.usecases

import com.example.domain.models.BaseResponse
import com.example.domain.models.NoteModel
import com.example.domain.repositories.NoteRepository
import com.example.domain.repositories.UserRepository
import java.time.LocalDateTime

class AddNoteUseCase constructor(
    private var noteRepository: NoteRepository,
    private var userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String?,
        title: String?,
        description: String?,
        subtitle: String?,
        image: String?,
        webLink: String?,
        color: Int
    ): BaseResponse<NoteModel> {
        if (title.isNullOrEmpty() || title.length < 3)
            return BaseResponse.ErrorResponse(message = "title is not valid") as BaseResponse<NoteModel>
        if (description.isNullOrEmpty() || description.length < 3)
            return BaseResponse.ErrorResponse(message = "description is not valid") as BaseResponse<NoteModel>
        if (subtitle.isNullOrEmpty())
            return BaseResponse.ErrorResponse(message = "subtitle is not valid") as BaseResponse<NoteModel>
        if (email.isNullOrEmpty())
            return BaseResponse.ErrorResponse(message = "Authorization key not found") as BaseResponse<NoteModel>
        if (!userRepository.isEmailExist(email))
            return BaseResponse.ErrorResponse(message = "Not Authorized") as BaseResponse<NoteModel>
        return try {
            val addNoteUseCase = noteRepository.addNote(
                email = email,
                title = title,
                description = description,
                subtitle = subtitle,
                image = image,
                date = LocalDateTime.now().toString(),
                webLink = webLink,
                color = color
            )
            BaseResponse.SuccessResponse(message = "Note has added successfully", data = addNoteUseCase)
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "unknown error ${e.message}") as BaseResponse<NoteModel>
        }
    }
}