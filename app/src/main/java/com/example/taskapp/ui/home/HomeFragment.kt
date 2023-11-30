package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.model.TaskModel
import com.example.taskapp.ui.task.TaskFragment.Companion.SAVE_RESULT_KEY
import com.example.taskapp.ui.task.TaskFragment.Companion.TASK_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this :: onLongClickItem)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTask.adapter = adapter
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }
    private fun onLongClickItem(tasks:TaskModel){
        showAlertDialog(tasks)
    }
    private fun showAlertDialog(tasks: TaskModel) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Deleted ? " + tasks.title)
            .setMessage("Are you sure ?")
            .setCancelable(true)
            .setPositiveButton("Да"){_,_ ->
                App.db.taskDao().delete(tasks)
                val data = App.db.taskDao().getAll()
                adapter.addTasks(data)
            }
            .setNegativeButton("Нет"){_,_ -> }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}