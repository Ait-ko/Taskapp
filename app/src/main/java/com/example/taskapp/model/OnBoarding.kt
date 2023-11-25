package com.example.taskapp.model

import java.io.Serializable

data class OnBoarding(
    val image: String? = null,
    val title: String? = null,
    val description: String? = null
) : Serializable
