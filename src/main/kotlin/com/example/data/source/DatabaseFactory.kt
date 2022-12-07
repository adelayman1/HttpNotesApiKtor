package com.example.data.source

import com.example.data.models.Notes
import com.example.data.models.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseFactory constructor(private var database: Database) {
    fun init() {
        transaction(database) {
            SchemaUtils.create(Notes)
            SchemaUtils.create(Users)
        }
    }
    companion object {
        suspend fun <T> dbQuery(block: suspend () -> T): T =
            newSuspendedTransaction(Dispatchers.IO) { block() }
    }
}