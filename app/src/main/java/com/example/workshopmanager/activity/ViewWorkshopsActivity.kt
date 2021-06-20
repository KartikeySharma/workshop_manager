package com.example.workshopmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopmanager.R
import com.example.workshopmanager.adapter.RecyclerViewAdapter
import com.example.workshopmanager.adapter.ShowAllAdapter
import com.example.workshopmanager.room.WorkshopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewWorkshopsActivity : AppCompatActivity() {

    private val uiScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_workshops)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view4)
        uiScope.launch {
            WorkshopRepository.getInstance(baseContext)!!.getAppDatabase().workshopDao()
                .deleteAll()
        }
        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(this@ViewWorkshopsActivity)
            // set the custom adapter to the RecyclerView
            adapter = ShowAllAdapter(context)
        }
    }
}