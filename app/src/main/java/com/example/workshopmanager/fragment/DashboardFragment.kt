package com.example.workshopmanager.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopmanager.R
import com.example.workshopmanager.activity.DashboardActivity
import com.example.workshopmanager.adapter.RVAdapter
import com.example.workshopmanager.adapter.RecyclerViewAdapter
import com.example.workshopmanager.room.Workshop
import com.example.workshopmanager.room.WorkshopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class DashboardFragment(val contextParam: Context) : Fragment() {
    private val uiScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycler_view1)
        uiScope.launch {
            WorkshopRepository.getInstance(requireContext())!!.getAppDatabase().workshopDao()
                .deleteAll()
        }
        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RecyclerViewAdapter(contextParam)
        }
    }
}