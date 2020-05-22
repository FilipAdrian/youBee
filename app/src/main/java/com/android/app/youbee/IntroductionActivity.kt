package com.android.app.youbee

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.drawer_activity.*


class IntroductionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.youbee_introduction)
        setContentView(R.layout.drawer_activity)
        setSupportActionBar(toolbar as Toolbar?);
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_activity,
            toolbar as Toolbar?,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_activity.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_activity.isDrawerOpen(GravityCompat.START)) {
            drawer_activity.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}