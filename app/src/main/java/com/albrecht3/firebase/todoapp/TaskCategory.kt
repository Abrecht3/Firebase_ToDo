package com.albrecht3.firebase.todoapp

sealed class TaskCategory {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}