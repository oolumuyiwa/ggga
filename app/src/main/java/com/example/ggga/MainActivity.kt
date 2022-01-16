package com.example.ggga

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import com.example.ggga.databinding.ActivityMainBinding
import java.lang.Exception
import android.util.Log
import android.widget.MediaController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val requestCall = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GGGA)

       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.OptionsMenu.setOnClickListener {
           val popupMenu = PopupMenu(this, it)
           popupMenu.setOnMenuItemClickListener { item ->
               when (item.itemId){
                   R.id.website -> {
                       val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/nJMY6na72_8"))
                       startActivity(intent)
                       true
                   }
                   R.id.call -> {
                       makePhoneCall()
                       true
                   }
                   R.id.about -> {
                       val intent= Intent(this, AboutActivity::class.java)
                       startActivity(intent)
                       true
                   }
                   R.id.message -> {
                       val intent= Intent(Intent.ACTION_SEND)
                       intent.putExtra(Intent.EXTRA_EMAIL, "godsgraciousgreenlandacademy@gmail.com")
                       intent.type = "message/rfc822"
                       startActivity(Intent.createChooser(intent, "Choose an App"))
                       true
                   }
                   else -> false
               }
           }
           popupMenu.inflate(R.menu.nav_menu)
           try {
               val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
               fieldMPopup.isAccessible = true
               val mPopup = fieldMPopup.get(popupMenu)
               mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java).invoke(mPopup, true)
           }
           catch(e: Exception){
               Log.e("Main", "Error showing menu icons.", e)
           }
           finally{
               popupMenu.show()
           }


       }

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.VidView)

        val onlineUri = Uri.parse("https://www.youtube.com/nJMY6na72_8")
        val offlineUri = Uri.parse("android.resource://$packageName/${R.raw.g3a_vid}")
        binding.VidView.setVideoURI(offlineUri)
        binding.VidView.requestFocus()
        binding.VidView.start()


    }

    private fun makePhoneCall() {
        val number: String = "+2348160846984"

            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    requestCall
                )
            } else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )  {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCall) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    }




  //  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
   //     menuInflater.inflate(R.menu.nav_menu, menu)
  //      return true
 //   }

  //  override fun onOptionsItemSelected(item: MenuItem): Boolean {
  //      when(item.itemId){
  //          R.id.main_menu -> Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show()
  //      }
  //      return super.onOptionsItemSelected(item)
  //  }


