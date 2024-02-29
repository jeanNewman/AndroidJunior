package com.jeandarwinnewmanrios.androidjunior.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.provider.Settings.Global.getString
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jeandarwinnewmanrios.androidjunior.R

class TaskViewHolder (view: View) : RecyclerView.ViewHolder(view)  {
    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task : Task){
        tvTask.text = task.name
        if(task.isSelected ){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        cbTask.isChecked = task.isSelected
        tvTask.text = task.name
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context,
                when(task.category){
                    TaskCategory.Business.toString() -> R.color.todo_business_category
                    TaskCategory.Personal.toString() -> R.color.todo_personal_category
                    else -> R.color.todo_other_category
                }
                )
        )
    }




}