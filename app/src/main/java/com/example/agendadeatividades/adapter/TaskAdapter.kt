package com.example.agendadeatividades.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendadeatividades.databinding.LayoutItemsBinding
import com.example.agendadeatividades.entity.Task
import com.example.agendadeatividades.util.MyHandlers

class TaskAdapter(var listTask: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = LayoutItemsBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listTask[position])
    }

    class MyViewHolder(private val binding: LayoutItemsBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.apply {
                binding.myHandlers = MyHandlers()
                binding.item = item
                executePendingBindings()
            }
        }
    }
}