package com.android.app.youbee

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.drawer_activity.*
import kotlinx.android.synthetic.main.drawer_header.view.*


class IntroductionActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "IntroductionActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_activity)
        setSupportActionBar(toolbar as Toolbar?)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_activity,
            toolbar as Toolbar?,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        nav_view.setNavigationItemSelectedListener(this)
        drawer_activity.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(fragment_container.id, InfoFragment()).commit()
            nav_view.setCheckedItem(R.id.nav_info)
        }
    }

    override fun onStart() {
        super.onStart()
        val headView: View = nav_view.getHeaderView(0)
        printUserDetails(headView)
    }

    override fun onBackPressed() {
        if (drawer_activity.isDrawerOpen(GravityCompat.START)) {
            drawer_activity.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun printUserDetails(headView: View) {
        if (!intent.hasExtra("user")) {
            return
        }
        val user = intent.getParcelableExtra<User>("user")
        headView.header_user_email.text = user.first_name
        headView.header_username.text = user.last_name
        d(TAG, user.toString())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_info -> supportFragmentManager.beginTransaction()
                .replace(fragment_container.id, InfoFragment()).commit()
            R.id.nav_kitchen -> supportFragmentManager.beginTransaction()
                .replace(fragment_container.id, RecipeActivity()).commit()
            else -> Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
        drawer_activity.closeDrawer(GravityCompat.START)
        return true
    }
}