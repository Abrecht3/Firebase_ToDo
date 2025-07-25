package com.albrecht3.firebase.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albrecht3.firebase.R

class TaskAdapter (private val tasks: List<Task>): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TaskViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int,
    ) {
        holder.render(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

}