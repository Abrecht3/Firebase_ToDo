package com.albrecht3.firebase.todoapp

data class Task (val name: String, val category: TaskCategory, var isSelected: Boolean = false)
