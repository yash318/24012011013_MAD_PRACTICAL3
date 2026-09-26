package com.example.mad_practical_3_24012011013

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.provider.AlarmClock
import android.provider.CallLog
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        implicitIntent()
        explicitIntent()
    }
    fun implicitIntent(){
        findViewById<Button>(R.id.browse).setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(findViewById<EditText>(R.id.url_text).text.toString())).also{
                startActivity(it)
            }
        }

        val callButton = findViewById<Button>(R.id.call)
        callButton.setOnClickListener {
            val number = findViewById<EditText>(R.id.phone_number).text.toString()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData("tel:$number".toUri())
            startActivity(intent)
        }

        findViewById<Button>(R.id.call_log).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://call_log/calls"))
            startActivity(intent)
        }

        findViewById<Button>(R.id.Gallery).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivity(intent)
        }

        findViewById<Button>(R.id.Camera).setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{startActivity(it)}
        }

        findViewById<Button>(R.id.Alarm).setOnClickListener {
            Intent(AlarmClock.ACTION_SHOW_ALARMS).also{startActivity(it)}
        }
    }
    fun explicitIntent(){
        findViewById<Button>(R.id.Login).setOnClickListener {
            Intent(this, LoginActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}
