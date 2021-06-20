package com.example.workshopmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopmanager.R


class RVAdapter(
    val contextParam: Context,
    private val name: ArrayList<String>, private val date: ArrayList<String>,
    private val btn: ArrayList<Boolean>
) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var set= mutableSetOf("random")
        var itemName: TextView = itemView.findViewById(R.id.workshopName)
        var itemDate: TextView = itemView.findViewById(R.id.workshopDate)
        var button: Button = itemView.findViewById(R.id.applyBtn)
        val sharedPreferences = contextParam.getSharedPreferences(R.string.shared_preferences.toString(), Context.MODE_PRIVATE)

        fun hideButton() {
            button.text = contextParam.getString(R.string.registered)
            button.isClickable = false
            button.setBackgroundResource(R.drawable.rounded_border_for_view_green)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.workshop_row, viewGroup, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        btn[i] = viewHolder.sharedPreferences.getBoolean("registered$i", false)
        viewHolder.itemName.text = name[i]
        viewHolder.itemDate.text = date[i]
        viewHolder.hideButton()
    }

    override fun getItemCount(): Int {
        return name.size
    }
}