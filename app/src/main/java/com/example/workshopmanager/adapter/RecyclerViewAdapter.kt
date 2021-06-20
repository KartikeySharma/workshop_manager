package com.example.workshopmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopmanager.R
import com.example.workshopmanager.room.Workshop
import com.example.workshopmanager.room.WorkshopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerViewAdapter(val contextParam: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val uiScope = CoroutineScope(Dispatchers.IO)

    private val name = arrayOf(
        "Android Development",
        "Web Development2",
        "C++ and C Workshop",
        "Video Editing",
        "Elementary French",
        "Machine Learning",
        "Data Science"
    )

    private val date = arrayOf(
        "20:00 18th May, 2021",
        "20:00 18th May, 2021",
        "20:00 18th May, 2021",
        "20:00 18th May, 2021",
        "20:00 18th May, 2021",
        "20:00 18th May, 2021",
        "10:00 30th May, 2021"
    )

    private val btn = arrayOf(
        false, false, false, false,
        false, false, false )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView = itemView.findViewById(R.id.workshopName)
        var itemDate: TextView = itemView.findViewById(R.id.workshopDate)
        var button: Button = itemView.findViewById(R.id.applyBtn)

        val sharedPreferences = contextParam.getSharedPreferences(R.string.shared_preferences.toString(), Context.MODE_PRIVATE)

        fun hideButton() {
            button.text = "Applied"
            button.isClickable = false
            button.setBackgroundResource(R.drawable.rounded_border_for_view_green)
        }

        fun storeData(i: Int) {
            uiScope.launch {
                val workshop = Workshop()
                workshop.name = name[i]
                workshop.date = date[i]
                workshop.applied = sharedPreferences.getBoolean("registered$i", false)
                WorkshopRepository.getInstance(itemView.context)!!.getAppDatabase().workshopDao()
                    .insert(workshop)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.workshop_row, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        val sharedPreferences = contextParam.getSharedPreferences(
            R.string.shared_preferences.toString(),
            Context.MODE_PRIVATE
        )
        viewHolder.itemName.text = name[i]
        viewHolder.itemDate.text = date[i]

        // ** SHOW OR HIDE THIS INSTANCE'S BUTTON - AND ONLY THIS INSTANCE'S BUTTON **
        btn[i] = viewHolder.sharedPreferences.getBoolean("registered$i", false)

        viewHolder.storeData(i)

        if (btn[i]) {
            viewHolder.hideButton()
        }

        viewHolder.button.setOnClickListener {
            /*val isloggedin= sharedPreferences.getBoolean("user_logged_in",false);
            if(!isloggedin){
                val activity= viewHolder.itemView.context as Activity
                val intent= Intent(activity, LoginRegisterActivity::class.java)
                contextParam.startActivity(intent)
            }
            else {*/
            if(!btn[i]) {
                btn[i] = true
                viewHolder.button.setBackgroundResource(R.drawable.rounded_border_for_view_green)
                viewHolder.button.isClickable= false
                viewHolder.sharedPreferences.edit().putBoolean("registered$i",true).apply()
                viewHolder.storeData(i)
                viewHolder.hideButton()
                uiScope.launch {
                    val workshop = Workshop()
                    workshop.name = name[i]
                    workshop.date = date[i]
                    workshop.applied = sharedPreferences.getBoolean("registered$i", false)
                    WorkshopRepository.getInstance(contextParam)!!.getAppDatabase().workshopDao()
                        .insert(workshop)
                }
                Toast.makeText(contextParam,"Registered Succesfully!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }
}