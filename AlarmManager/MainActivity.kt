package com.example.a20201117

import android.content.Context
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        // Vibrate system 설정
        button1.setOnClickListener {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            // 휴대폰 SDK 버전 확인하기
            if(Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000,10))
            }
            else {vibrator.vibrate(1000)}
        }

        // Ringtone 내장 사운드 출력하기
        button2.setOnClickListener {
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) as Uri
            val ringtone = RingtoneManager.getRingtone(applicationContext, uri)
            ringtone.play()
        }

        // 지정해둔 파일로 소리 출력하기
        button3.setOnClickListener {
            val player = MediaPlayer.create(applicationContext, R.raw.beep) as MediaPlayer
            player.start()
        }
    }
}