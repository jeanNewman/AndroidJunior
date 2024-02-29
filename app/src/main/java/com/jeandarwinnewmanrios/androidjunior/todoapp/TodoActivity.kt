package com.jeandarwinnewmanrios.androidjunior.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jeandarwinnewmanrios.androidjunior.R
import com.jeandarwinnewmanrios.androidjunior.todoapp.TaskCategory.*

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Other,
        Personal
    )

    private val tasks = mutableListOf(
        Task("PruebaBusiness", Business.toString()),
        Task("PruebaOther", Other.toString()),
        Task("PruebaPersonal", Personal.toString()),
    )
    private lateinit var rwCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rwTasks: RecyclerView

    private lateinit var taskAdapter: TaskAdapter

    private lateinit var fbAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListener()
    }

    private fun initComponent(){
        rwCategories = findViewById(R.id.rwCategories)
        rwTasks = findViewById(R.id.rwTasks)
        fbAddTask = findViewById(R.id.fbAddTask)

    }

    private fun initUI(){
        categoriesAdapter = CategoriesAdapter(categories) {updateCategories(it)}
        taskAdapter = TaskAdapter(tasks) { onItemSelected(it) }
        rwCategories.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rwTasks.layoutManager = LinearLayoutManager(this)
        rwCategories.adapter = categoriesAdapter
        rwTasks.adapter = taskAdapter
    }

    private fun initListener(){
        fbAddTask.setOnClickListener(){
            showDialog()
        }
    }

    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgTask: RadioGroup = dialog.findViewById(R.id.rgTask)
        dialog.show()
        btnAddTask.setOnClickListener {
            val selectedId = rgTask.checkedRadioButtonId
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedRadioButton = rgTask.findViewById<RadioButton>(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.business) -> Business
                    getString(R.string.personal) -> Personal
                    else -> Other
                }

                tasks.add(Task(currentTask, currentCategory.toString()))
                updateTask()
                dialog.hide()
           }

        }
    }

    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()
    }

    private fun onItemSelected(position: Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTask()
    }

    private fun updateTask(){
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { x -> selectedCategories.any{ x.category == it.toString() }}
        taskAdapter.tasks = newTasks
        taskAdapter.notifyDataSetChanged()
    }
}