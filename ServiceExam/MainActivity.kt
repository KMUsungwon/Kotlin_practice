package com.example.serviceexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun mOnClick(v: View) {
        val intent = Intent(this, MyService::class.java)

        when(v.id) {
            R.id.btnPlayMusic -> {
                intent.setAction("andbook.example.PLAYMUSIC")
                startService(intent)
            }
            R.id.btnDownload -> {
                intent.setAction("andbook.example.DOWNLOAD")
                startService(intent)
            }
            R.id.btnStopService -> {stopService(intent)}
        }
    }
}