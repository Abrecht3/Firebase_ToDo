package com.albrecht3.firebase.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albrecht3.firebase.R


class CategoriesAdapter(private val categories: List<TaskCategory>) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoriesViewHolder,
        position: Int,
    ) {
        holder.render(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}