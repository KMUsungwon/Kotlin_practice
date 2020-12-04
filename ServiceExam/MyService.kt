package com.example.serviceexam

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {


    override fun onBind(intent: Intent): IBinder? {
        TODO("Return the communication channel to the service.")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("test", "OnCreate() 호출!")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent?.action.equals("andbook.example.PLAYMUSIC")) {
            MusicThread().start()
        }
        else if (intent?.action.equals("andbook.example.DOWNLOAD")) {
            DownloadThread().start()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("test", "onDestroy() 호출!")
    }
}

class DownloadThread : Thread() {
    override fun run() {
        Log.d("test", "파일 다운로드 시작!")
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {e.printStackTrace()}
        Log.d("test", "파일 다운로드 종료!")
    }
}

class MusicThread : Thread() {
    override fun run() {
        Log.d("test", "음악 재생 시작!")
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {e.printStackTrace()}
        Log.d("test", "음악 재생 종료!")
    }
}
