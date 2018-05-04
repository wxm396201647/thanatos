package com.thanatos.read

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    message.text = getString(R.string.title_home)
                    true
                }
                R.id.navigation_dashboard -> {
                    message.text = getString(R.string.title_dashboard)
                    true
                }
                R.id.navigation_notifications -> {
                    message.text = getString(R.string.title_notifications)
                    true
                }
            }
            false
        }
    }

}
