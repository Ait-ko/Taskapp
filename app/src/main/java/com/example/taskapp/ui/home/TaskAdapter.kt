package com.example.taskapp.ui.home

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide.init
import com.example.taskapp.databinding.ItemTaskBinding
import com.example.taskapp.model.TaskModel

class TaskAdapter (private  val onClick:(TaskModel)-> Unit , private  val onLongClick : (TaskModel)-> Unit)  : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val taskList = arrayListOf<TaskModel>()
    fun addTasks(tasks: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        return holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {

        fun bind(task: TaskModel) {
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.description
            itemView.setOnClickListener {
                onClick(task)
            }
            itemView.setOnLongClickListener {
                onLongClick(taskList[adapterPosition])
                true
        }






        }
    }
}


