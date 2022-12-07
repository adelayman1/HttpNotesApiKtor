package com.example.domain.repositories

import com.example.domain.models.UserModel

interface UserRepository {
    suspend fun addUser(email: String, password: String, name: String): UserModel?
    suspend fun getUserByEmail(email: String): UserModel?
    suspend fun isEmailExist(email: String): Boolean
}