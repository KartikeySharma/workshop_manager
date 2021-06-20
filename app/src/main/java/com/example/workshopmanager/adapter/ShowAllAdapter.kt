package com.example.workshopmanager.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopmanager.R
import com.example.workshopmanager.activity.LoginRegisterActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ShowAllAdapter(val contextParam: Context) : RecyclerView.Adapter<ShowAllAdapter.ViewHolder>() {

    private var activity: Activity? = null

    fun ShowAllAdapter(activity: Activity?) {
        this.activity = activity
    }

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

        fun hideButton() {
            button.text = "Applied"
            button.isClickable = false
            button.setBackgroundResource(R.drawable.rounded_border_for_view_green)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ShowAllAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.workshop_row, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.itemName.text = name[i]
        viewHolder.itemDate.text = date[i]

        // ** SHOW OR HIDE THIS INSTANCE'S BUTTON - AND ONLY THIS INSTANCE'S BUTTON **
        btn[i] = false

        if (btn[i]) {
            viewHolder.hideButton()
        }

        viewHolder.button.setOnClickListener {

            val alterDialog = androidx.appcompat.app.AlertDialog.Builder(contextParam)
            alterDialog.setTitle("Alert")
            alterDialog.setMessage("You need to login to register for any workshops")
            alterDialog.setPositiveButton("Login")
            { _, _ ->
                val activity= viewHolder.itemView.context as Activity
                val intent= Intent(activity, LoginRegisterActivity::class.java)
                contextParam.startActivity(intent)
                ActivityCompat.finishAffinity(contextParam as Activity)
                //app login user credentials are erased and it takes the user to the login screen
            }
            alterDialog.setCancelable(false)
            alterDialog.create()
            alterDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }

}