package com.example.domain.models

//@JsonInclude(JsonInclude.Include.NON_NULL)
data class NoteModel(
    var noteId: String = "0",
    var email: String,
    var title: String,
    var subtitle: String,
    var description: String,
    var date: String,
    var image: String? = null,
    var webLink: String? = null,
    val color:Int
)
