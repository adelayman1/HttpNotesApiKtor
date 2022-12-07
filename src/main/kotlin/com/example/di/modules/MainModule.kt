package com.example.di.modules

import com.example.data.repositories.NoteRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.data.source.DatabaseFactory
import com.example.data.source.dao.noteDao.NoteDAO
import com.example.data.source.dao.noteDao.NoteDAOImpl
import com.example.data.source.dao.userDao.UserDAO
import com.example.data.source.dao.userDao.UserDAOImpl
import com.example.domain.repositories.NoteRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.*
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val mainModule = module {
    single {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        Database.connect(jdbcURL, driverClassName)
    }
    single {
        DatabaseFactory(get())
    }
    single<UserDAO> {
        UserDAOImpl()
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<NoteDAO> {
        NoteDAOImpl()
    }
    single<NoteRepository> {
        NoteRepositoryImpl(get())
    }

    single {
        RegisterUseCase(get())
    }
    single {
        LoginUseCase(get())
    }

    single {
        GetAllUserNotesUseCase(get(),get())
    }

    single {
        GetNoteDetailsUseCase(get(),get())
    }

    single {
        UpdateNoteDetailsUseCase(get(),get())
    }

    single {
        SearchNoteUseCase(get(),get())
    }
    single {
        DeleteNoteUseCase(get(),get())
    }
    single {
        AddNoteUseCase(get(),get())
    }
}