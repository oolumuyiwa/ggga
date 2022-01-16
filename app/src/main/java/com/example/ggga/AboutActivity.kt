package com.example.ggga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GGGA)
        setContentView(R.layout.activity_about)

        val actionBar = supportActionBar

       // if (actionBar != null){
       //     actionBar.title = "About GGGA"
       // } cleaner version of code below
        actionBar!!.title = "About GGGA"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}