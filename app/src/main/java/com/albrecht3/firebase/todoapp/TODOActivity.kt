package com.albrecht3.firebase.todoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.albrecht3.firebase.R
import android.app.Dialog
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TODOActivity : AppCompatActivity() {

    private val categories = listOf<TaskCategory>(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    private val tasks = mutableListOf(
        Task("Prueba Business", TaskCategory.Business),
        Task("Prueba Personal", TaskCategory.Personal),
        Task("Prueba Other", TaskCategory.Other),
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView

    private lateinit var taskAdapter: TaskAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        initComponents()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: MaterialButton = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()){
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.business) -> TaskCategory.Business
                    getString(R.string.personal)-> TaskCategory.Personal
                    else -> TaskCategory.Other
                }

                tasks.add(Task(currentTask, currentCategory))
                updateTasks()
                dialog.hide()
            }
        }
        dialog.show()
    }

    private fun initComponents() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        taskAdapter = TaskAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = taskAdapter
    }

    private fun updateTasks() {
        taskAdapter.notifyDataSetChanged()
    }
}