package com.example.workshopmanager.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopmanager.R
import com.example.workshopmanager.adapter.RVAdapter
import com.example.workshopmanager.room.Workshop
import com.example.workshopmanager.room.WorkshopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisteredFragment(val contextParam: Context) : Fragment() {

    private val uiScope = CoroutineScope(Dispatchers.IO)
    private var name: ArrayList<String> = ArrayList()
    private var date: ArrayList<String> = ArrayList()
    private var btn: ArrayList<Boolean> = ArrayList()
    private var size = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycler_view3)
        recyclerView.apply {
            fetchData()
            layoutManager = LinearLayoutManager(activity)
            adapter = RVAdapter(contextParam,name,date,btn)

            adapter?.notifyDataSetChanged()
        }
    }
    private fun fetchData() {
        uiScope.launch {
            val list: List<Workshop> =
                WorkshopRepository.getInstance(requireContext())!!.getAppDatabase().workshopDao()
                    .getAll()
            for (workshop in list) {
                if (workshop.applied) {
                    name.add(workshop.name)
                    date.add(workshop.date)
                    btn.add(workshop.applied)
                    size++
                }
            }
        }
//        Toast.makeText(requireContext(),"stored "+(size+1)+" new workshop", Toast.LENGTH_SHORT).show()
    }
}