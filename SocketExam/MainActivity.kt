package com.example.socketexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.TextView

// 소켓 네트워크 통신 예제

class MainActivity : AppCompatActivity() {
    private lateinit var mServerThread: ServerThread
    private lateinit var mtextOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mtextOutput = findViewById(R.id.textOutput)
    }

    override fun onDestroy() {
        super.onDestroy()
        android.os.Process.killProcess(android.os.Process.myPid())
    }


    private var mMainHandler: MainHandler = MainHandler()

    inner class MainHandler: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what) {
                1 -> {mtextOutput.append(msg.obj.toString())}
            }
        }
    }

    fun mOnClick(v: View) {
        when(v.id) {
            R.id.btnStart -> {
                if(mServerThread == null) {
                    mServerThread = ServerThread(this, mMainHandler)
                    mServerThread.start()
                }
            }
            R.id.btnStop -> {finish()}
        }
    }
}