package com.example.taskapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}