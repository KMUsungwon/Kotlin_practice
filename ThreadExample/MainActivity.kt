package com.example.threadexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var isRunning=true // 스레드 반복을 위한 변수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //스레드 시작 버튼
        button.setOnClickListener {
            isRunning = true
            val thread=ThreadClass()
            thread.start()
            Toast.makeText(this,"스레드 시작",Toast.LENGTH_SHORT).show()
        }
        //스레드 종료 버튼
        button2.setOnClickListener {
            isRunning = false
            Toast.makeText(this,"스레드 종료",Toast.LENGTH_SHORT).show()
        }
    }
    //0부터 스레드 종료 전까지 Console 에 값 카운트
    inner class ThreadClass:Thread(){
        var value = 0
        override fun run(){
            while(isRunning){
                SystemClock.sleep(1000)
                Log.d("쓰레드",value.toString())
                value+=1
            }
        }
    }
    // 어플 종료 시 스레드 종료
    override fun onDestroy() {
        super.onDestroy()
        isRunning=false
    }

}