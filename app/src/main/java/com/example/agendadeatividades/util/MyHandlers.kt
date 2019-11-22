package com.example.agendadeatividades.util

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.agendadeatividades.R

class MyHandlers {
    fun goToFragment(view: View){
        view.findNavController().navigate(R.id.toTaskFragment)
    }

    fun goToTaskFragment(it: View, idActivity: Int){
        val bundle = bundleOf("idActivity" to idActivity)
        it.findNavController().navigate(R.id.toTaskFragment, bundle)
    }
}