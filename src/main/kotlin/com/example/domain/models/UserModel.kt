package com.example.domain.models


data class UserModel(
    var userID: String = "-1",
    var userToken: String? = null,
    var userName: String,
    var email: String
)