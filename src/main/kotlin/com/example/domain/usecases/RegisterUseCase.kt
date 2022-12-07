package com.example.domain.usecases

import com.example.data.utilities.generateToken
import com.example.domain.models.BaseResponse
import com.example.domain.models.UserModel
import com.example.domain.repositories.UserRepository

class RegisterUseCase constructor(private var userRepository: UserRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): BaseResponse<UserModel> {
        return try {
            //  check is name valid
            if (name.isBlank() || name.length < 3)
                return BaseResponse.ErrorResponse(message = "Name is too short") as BaseResponse<UserModel>
            // check is email valid
            if (email.isBlank() || email.length < 5)
                return BaseResponse.ErrorResponse(message = "Email is too short") as BaseResponse<UserModel>
            // check is password valid
            if (password.isBlank() || password.length < 5)
                return BaseResponse.ErrorResponse(message = "Password is too short") as BaseResponse<UserModel>
            // check is email has been used before
            if (userRepository.isEmailExist(email))
                return BaseResponse.ErrorResponse(message = "This email has been used before") as BaseResponse<UserModel>
            val registerResult = userRepository.addUser(email, password, name)
            return if (registerResult != null) {
                val accessToken = generateToken(email = email)
                registerResult.userToken = accessToken
                BaseResponse.SuccessResponse(
                    message = "Registration done successfully",
                    data = registerResult
                )
            } else BaseResponse.ErrorResponse(message = "Unknown Error") as BaseResponse<UserModel>
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "unknown error ${e.message}") as BaseResponse<UserModel>
        }
    }
}