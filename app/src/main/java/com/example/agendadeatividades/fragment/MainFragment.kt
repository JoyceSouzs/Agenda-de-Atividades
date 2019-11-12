package com.example.agendadeatividades.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendadeatividades.R
import com.example.agendadeatividades.adapter.ActivityAdapter
import com.example.agendadeatividades.repository.ActivityRepository
import com.example.agendadeatividades.repository.MyRoom
import com.example.agendadeatividades.viewmodel.ActivityViewModel
import com.example.agendadeatividades.viewmodel.ActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    lateinit var viewModel: ActivityViewModel
    lateinit var adapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_main, container, false)
        binding.rec_view.layoutManager = LinearLayoutManager((activity as AppCompatActivity).baseContext)

        init()

        viewModel.getActivities().observe(this, Observer { activities ->
            activities.forEach { activity ->
                adapter = ActivityAdapter(activities)
                rec_view.adapter = adapter
                Log.d("Tarefa",activity.toString())
            }
        })
        return binding
    }

    private fun init() {
        val db = MyRoom.getInstance((activity as AppCompatActivity).applicationContext)
        db?.let {
            val activityRepository = ActivityRepository(it.getActivityDao())
            val activityViewModelFactory = ActivityViewModelFactory(activityRepository)

            viewModel = ViewModelProviders
                .of(this, activityViewModelFactory)
                .get(ActivityViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        float_button.setOnClickListener {
            it.findNavController().navigate(R.id.toActivityFragment)
        }
    }

}
