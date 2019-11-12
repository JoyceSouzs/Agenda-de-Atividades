package com.example.agendadeatividades.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.agendadeatividades.R
import com.example.agendadeatividades.entity.Activity
import kotlinx.android.synthetic.main.layout_items.view.*

class ActivityAdapter(var listAtivity: List<Activity>) :
    RecyclerView.Adapter<ActivityAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityAdapter.MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_items,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAtivity.size
    }

    override fun onBindViewHolder(holder: ActivityAdapter.MyViewHolder, position: Int) {
        holder.titleTextView.text = listAtivity[position].title
        holder.descriptionTextView.text = listAtivity[position].description

        holder.cardView.setOnClickListener {
            val bundle = bundleOf("idActivity" to listAtivity[position].idTitle)
            it.findNavController().navigate(R.id.toActivityFragment,bundle)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var titleTextView = view.text_view_title
            var descriptionTextView = view.text_view_description
            var cardView = view.card_view
    }
}