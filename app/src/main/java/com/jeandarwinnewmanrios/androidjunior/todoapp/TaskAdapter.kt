package com.jeandarwinnewmanrios.androidjunior.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeandarwinnewmanrios.androidjunior.R

class TaskAdapter(var tasks:List<Task>, private val onTaskSelected: (Int) -> Unit): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_tasks,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position])
        holder.itemView.setOnClickListener{onTaskSelected(position)}
    }

    override fun getItemCount() = tasks.size



}