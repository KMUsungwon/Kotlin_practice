package com.example.bindserviceexam

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyService : Service() {

    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    class LocalBinder : Binder() {
        fun getService(): MyService {
            return MyService()
        }
    }

    fun CalcNum(m : Int, n : Int) : Int {
        return m*n
    }
}
