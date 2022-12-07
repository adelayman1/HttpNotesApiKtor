package com.example.domain.usecases

import com.example.data.utilities.generateToken
import com.example.domain.models.BaseResponse
import com.example.domain.models.UserModel
import com.example.domain.repositories.UserRepository

class LoginUseCase constructor(private var userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): BaseResponse<UserModel> {
        return try {
            // check is email valid
            if (email.isBlank() || email.length < 5)
                return BaseResponse.ErrorResponse(message = "Email is too short") as BaseResponse<UserModel>
            // check is password valid
            if (password.isBlank() || password.length < 5)
                return BaseResponse.ErrorResponse(message = "Password is too short") as BaseResponse<UserModel>
            // get user with given data
            val loginResult = userRepository.getUserByEmail(email)
            if(loginResult!=null){
                val accessToken = generateToken(email = email)
                loginResult.userToken = accessToken
                BaseResponse.SuccessResponse(
                    message = "Login done successfully",
                    data = loginResult
                )
                return BaseResponse.SuccessResponse(message = "Login done successfully", data = loginResult)
            }else{
                return BaseResponse.ErrorResponse(message = "Email is not exist")  as BaseResponse<UserModel>
            }
        } catch (e: Exception) {
            return BaseResponse.ErrorResponse(message = "${e.message}") as BaseResponse<UserModel>
        }
    }
}