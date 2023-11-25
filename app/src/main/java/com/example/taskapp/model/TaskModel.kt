package com.example.taskapp.model

import java.io.Serializable

data class TaskModel(
    val title: String? = null,
    val description: String? = null
) : Serializable
