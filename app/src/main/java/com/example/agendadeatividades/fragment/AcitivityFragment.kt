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
import com.example.agendadeatividades.entity.Activity
import com.example.agendadeatividades.repository.ActivityRepository
import com.example.agendadeatividades.repository.MyRoom
import com.example.agendadeatividades.viewmodel.ActivityViewModel
import com.example.agendadeatividades.viewmodel.ActivityViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_activity.*
import kotlinx.android.synthetic.main.fragment_activity.view.*

/**
 * A simple [Fragment] subclass.
 */
class AcitivityFragment : Fragment() {
    lateinit var viewModel: ActivityViewModel
   // val ARG_BACK: Int = 1
    var idTitle: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_activity, container, false)
        init()
        (activity as AppCompatActivity).setSupportActionBar(binding.tool_bar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Obs só pq é fragment, informa activity q tem um option Menu
        setHasOptionsMenu(true)
        return binding
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> (activity as AppCompatActivity).onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun init() {
        val db = MyRoom.getInstance((activity as AppCompatActivity).applicationContext)
        db?.let {
            val activityRepository = ActivityRepository(it.getActivityDao())
            val activityViewModelFactory = ActivityViewModelFactory(activityRepository)

            viewModel = ViewModelProviders
                .of((activity as AppCompatActivity), activityViewModelFactory)
                .get(ActivityViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idRecover = arguments?.getInt("idActivity", 0)
        if (idRecover != null && idRecover != 0) {
            idTitle = idRecover
            alterar(idTitle)
        }

        button_save.setOnClickListener {
            var description = edit_text_description.text.toString()
            var title = edit_text_name.text.toString()

            if (description.equals("") || title.equals("")) {
                Snackbar.make(view, R.string.msg_text_empty, Snackbar.LENGTH_SHORT).show()
            } else {
                val activity = Activity(idTitle, title, description)
                viewModel.salvarActivity(activity)
            }
            Snackbar.make(view, R.string.msg_text_sucess, Snackbar.LENGTH_SHORT).show()
            it.findNavController().navigate(R.id.toMainFragment)
        }

        button_delete.setOnClickListener {
            delete(idTitle)
            it.findNavController().navigate(R.id.toMainFragment)
        }
    }

    fun alterar(idTitle: Int) {
        var activity = viewModel.getActivityId(idTitle)
        var description = activity.description
        var title = activity.title

        edit_text_name.setText(title)
        edit_text_description.setText(description)
    }

    fun delete(idTitle: Int) {
        if (idTitle != 0) {
            var activity = viewModel.getActivityId(idTitle)
            viewModel.deleteActivity(activity)
        }
    }
}
