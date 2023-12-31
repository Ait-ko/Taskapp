package com.example.taskapp.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentTaskBinding
import com.example.taskapp.model.TaskModel
import com.example.taskapp.ui.home.HomeFragment


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskModel = arguments?.getSerializable(HomeFragment.TASK_EDIT_KEY) as TaskModel?
        if (taskModel != null){
            binding.btnSave.text = "Update"
            binding.etTitle.setText(taskModel.title)
            binding.etDescription.setText(taskModel.description)


        }
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            if (binding.etTitle.text.toString().trim().isEmpty()){
                binding.etTitle.error = "Enter Title"
            } else {
                val data = TaskModel(title = title, description = description)
                App.db.taskDao().insert(data)
                findNavController().navigateUp()
            }
        }
    }

    companion object {
        const val SAVE_RESULT_KEY = "task.result.key"
        const val TASK_KEY = "task.key"
    }

    }
