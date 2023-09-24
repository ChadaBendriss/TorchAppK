package com.example.torchapp

import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private var isFlashlightOn = false
    private val cameraManager by lazy { getSystemService(CAMERA_SERVICE) as CameraManager }
    private val cameraId by lazy { cameraManager.cameraIdList[0] }
    private val cameraFlash by lazy { packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH) }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageButton: ImageButton = findViewById(R.id.imOffButton)

        imageButton.setOnClickListener {
            if (cameraFlash) {
                toggleFlashlight()
                imageButton.setImageResource(
                    if (isFlashlightOn) R.drawable.torchon else R.drawable.torchoff
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun toggleFlashlight() {
        try {
            isFlashlightOn = !isFlashlightOn
            cameraManager.setTorchMode(cameraId, isFlashlightOn)
        } catch (e: Exception) {

        }
    }
}
