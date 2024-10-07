package com.example.flashlight

import android.content.Context
import android.graphics.Color
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private var flashSwitch=false
    private lateinit var ivSvitch:ImageView
    private lateinit var tvTitle:TextView
    private lateinit var cameraManager:CameraManager
    private lateinit var saund:MediaPlayer
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initViews() {
        ivSvitch=findViewById(R.id.iv_switch)
        tvTitle=findViewById(R.id.tv_title)
        ivSvitch.setOnClickListener {
            flashSwitch= !flashSwitch
            if (flashSwitch) {
                cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
                flashSwitch=true
                saund = MediaPlayer.create(this, R.raw.flashlight_button_click)
                saund.start()
                cameraManager.setTorchMode(cameraManager.cameraIdList[0],flashSwitch)
                tvTitle.setTextColor(Color.GREEN)
                ivSvitch.setImageResource(R.drawable.on)
            }
            else {
                cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
                flashSwitch=false
                saund = MediaPlayer.create(this, R.raw.flashlight_button_click)
                saund.start()
                cameraManager.setTorchMode(cameraManager.cameraIdList[0],flashSwitch)
                tvTitle.setTextColor(Color.RED)
                ivSvitch.setImageResource(R.drawable.off)
            }
        }


    }
}