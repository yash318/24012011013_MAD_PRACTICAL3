package com.example.mad_practical_3_24012011013
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editTextText2: EditText
    private lateinit var editTextPhone: EditText

    private lateinit var btn_browse: Button
    private lateinit var btn_call: Button
    private lateinit var btn_callLog: Button
    private lateinit var btn_gallery: Button
    private lateinit var btn_camera: Button
    private lateinit var btn_alarm: Button
    private lateinit var btn_login: Button

    private val callPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {
                makePhoneCall()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Connect XML IDs with Kotlin
        editTextText2 = findViewById(R.id.editTextText2)
        editTextPhone = findViewById(R.id.editTextPhone)

        btn_browse = findViewById(R.id.btn_browse)
        btn_call = findViewById(R.id.btn_call)
        btn_callLog = findViewById(R.id.btn_callLog)
        btn_gallery = findViewById(R.id.btn_gallery)
        btn_camera = findViewById(R.id.btn_camera)
        btn_alarm = findViewById(R.id.btn_alarm)
        btn_login = findViewById(R.id.btn_login)

        // Browse
        btn_browse.setOnClickListener {
            openWebsite()
        }

        // Call
        btn_call.setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                makePhoneCall()
            } else {
                callPermissionLauncher.launch(
                    Manifest.permission.CALL_PHONE
                )
            }
        }

        // Call Log
        btn_callLog.setOnClickListener {
            openCallLog()
        }

        // Gallery
        btn_gallery.setOnClickListener {
            openGallery()
        }

        // Camera
        btn_camera.setOnClickListener {
            openCamera()
        }

        // Alarm
        btn_alarm.setOnClickListener {
            setAlarm()
        }

        // Login
        btn_login.setOnClickListener {

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            startActivity(intent)
        }
    }

    // Open Website
    private fun openWebsite() {

        val url = editTextText2.text.toString()

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        startActivity(intent)
    }

    // Make Phone Call
    private fun makePhoneCall() {

        val phoneNumber = editTextPhone.text.toString()

        val intent = Intent(
            Intent.ACTION_CALL,
            Uri.parse("tel:$phoneNumber")
        )

        startActivity(intent)
    }

    // Open Call Log
    private fun openCallLog() {

        val intent = Intent(Intent.ACTION_VIEW)

        intent.type = CallLog.Calls.CONTENT_TYPE

        startActivity(intent)
    }

    // Open Gallery
    private fun openGallery() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)

        intent.type = "image/*"

        startActivity(intent)
    }

    // Open Camera
    private fun openCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivity(intent)
    }

    // Set Alarm
    private fun setAlarm() {

        val intent = Intent(AlarmClock.ACTION_SET_ALARM)

        intent.putExtra(
            AlarmClock.EXTRA_MESSAGE,
            "Practical Alarm"
        )

        intent.putExtra(
            AlarmClock.EXTRA_HOUR,
            10
        )

        intent.putExtra(
            AlarmClock.EXTRA_MINUTES,
            30
        )

        startActivity(intent)
    }
}