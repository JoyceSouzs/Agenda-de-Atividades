package com.example.agendadeatividades.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendadeatividades.adapter.TaskAdapter
import com.example.agendadeatividades.databinding.FragmentMainBinding
import com.example.agendadeatividades.repository.TaskRepository
import com.example.agendadeatividades.repository.MyRoom
import com.example.agendadeatividades.util.MyHandlers
import com.example.agendadeatividades.viewmodel.TaskViewModel
import com.example.agendadeatividades.viewmodel.TaskViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    lateinit var viewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater,container,false)
        binding.recView.layoutManager = LinearLayoutManager((activity as AppCompatActivity).baseContext)
        binding.myHandlers = MyHandlers()
        init()

        viewModel.getTasks().observe(this, Observer { tasks ->
            tasks.forEach { task ->
                binding.recView.adapter = TaskAdapter(tasks)
            }
        })
        return binding.root
    }

    private fun init() {
        val db = MyRoom.getInstance((activity as AppCompatActivity).applicationContext)
        db?.let {
            val activityRepository = TaskRepository(it.getActivityDao())
            val activityViewModelFactory = TaskViewModelFactory(activityRepository)

            viewModel = ViewModelProviders
                .of(this, activityViewModelFactory)
                .get(TaskViewModel::class.java)
        }
    }
}
