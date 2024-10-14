package com.example.myjetpackapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myjetpackapplication.TodoData

@Database(entities = [TodoData::class], version = 2)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object {

        const val NAME = "todo_database"
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE TodoData ADD COLUMN taskDescription TEXT")
            }
        }

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    NAME
                )
                    .addMigrations(MIGRATION_1_2) // Add migration here
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
