package com.example.agendadeatividades.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.agendadeatividades.R
import com.example.agendadeatividades.databinding.FragmentTaskBinding
import com.example.agendadeatividades.entity.Task
import com.example.agendadeatividades.repository.MyRoom
import com.example.agendadeatividades.repository.TaskRepository
import com.example.agendadeatividades.viewmodel.TaskViewModel
import com.example.agendadeatividades.viewmodel.TaskViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class TaskFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel
    var idTitle: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTaskBinding.inflate(inflater, container, false)
        init()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Obs só pq é fragment, informa activity que tem um option Menu
        setHasOptionsMenu(true)

        val idRecover = arguments?.getInt("idActivity", 0)
        if (idRecover != null && idRecover != 0) {
            idTitle = idRecover
            val task = viewModel.getTaskId(idRecover)
            binding.task = task
        }

        binding.buttonSave.setOnClickListener {
            val description = binding.editTextDescription.text.toString()
            val title = binding.editTextName.text.toString()
            if (description == "" || title == "") {
                Snackbar.make(it, R.string.msg_text_empty, Snackbar.LENGTH_SHORT).show()
            } else if (idTitle != 0) {
                val task = Task(idTitle, title, description)
                viewModel.saveTask(task)
                Snackbar.make(it, R.string.msg_text_sucess, Snackbar.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.toMainFragment)
            } else {
                val task = Task(0, title, description)
                viewModel.saveTask(task)
                Snackbar.make(it, R.string.msg_text_sucess, Snackbar.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.toMainFragment)
            }
        }

        binding.buttonDelete.setOnClickListener {
            val description = binding.editTextDescription.text.toString()
            val title = binding.editTextName.text.toString()
            if (description == "" || title == "") {
                Snackbar.make(it, R.string.msg_text_delete_error, Snackbar.LENGTH_SHORT).show()
            } else {
                MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                    .setTitle(R.string.title_text_delete)
                    .setMessage(R.string.msg_text_ask_delete)
                    .setPositiveButton(R.string.button_remove) { dialog, which ->
                        delete(idTitle)
                        Snackbar.make(it, R.string.msg_text_delete_sucess, Snackbar.LENGTH_SHORT)
                            .show()
                        it.findNavController().navigate(R.id.toMainFragment)
                    }
                    .setNegativeButton(R.string.button_cancel, null)
                    .show()
            }
        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> (activity as AppCompatActivity).onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        val db = MyRoom.getInstance((activity as AppCompatActivity).applicationContext)
        db?.let {
            val activityRepository = TaskRepository(it.getActivityDao())
            val activityViewModelFactory = TaskViewModelFactory(activityRepository)

            viewModel = ViewModelProviders
                .of((activity as AppCompatActivity), activityViewModelFactory)
                .get(TaskViewModel::class.java)
        }
    }

    private fun delete(idTitle: Int) {
        if (idTitle != 0) {
            val task = viewModel.getTaskId(idTitle)
            viewModel.deleteTask(task)
        }
    }
}
