package com.example.workshopmanager.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.workshopmanager.R
import com.example.workshopmanager.fragment.DashboardFragment
import com.example.workshopmanager.fragment.LoginFragment

class LoginRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        val sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences),
            Context.MODE_PRIVATE
        )

        if (sharedPreferences.getBoolean("user_logged_in", false)) {
            val intent = Intent(this@LoginRegisterActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish();
        } else {
            openLoginFragment()
        }
    }

    fun openLoginFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.frameLayout,
            LoginFragment(this)
        )
        transaction.commit()
        supportActionBar?.title = "DashboardActivity"

    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                openLoginFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}