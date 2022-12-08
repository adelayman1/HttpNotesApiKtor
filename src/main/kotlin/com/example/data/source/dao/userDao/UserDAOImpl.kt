package com.example.data.source.dao.userDao

import com.example.data.models.Users
import com.example.data.source.DatabaseFactory.Companion.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDAOImpl : UserDAO {
    override suspend fun insetUser(userEmail: String,userPassword:String,userName:String): ResultRow? {
        return dbQuery {
            val insertStatement = Users.insert { users ->
                users[email] = userEmail
                users[password] = userPassword
                users[name] = userName
            }
            insertStatement.resultedValues?.singleOrNull()
        }
    }

    override suspend fun getUserByEmail(email: String): ResultRow? {
        return dbQuery {
            Users.select { Users.email eq email }
                .singleOrNull()
        }
    }
}
