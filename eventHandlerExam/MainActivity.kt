package com.example.eventhandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Event Handler Example
        findViewById<EditText>(R.id.editText).setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN) { // 특정 키를 눌렀을 때 이벤트 처리
                when(keyCode) {
                    KeyEvent.KEYCODE_ENTER -> { // 엔터를 눌렀을 때
                        Toast.makeText(v.context, "Enter Click", Toast.LENGTH_SHORT).show()
                        return@setOnKeyListener true
                    }
                    KeyEvent.KEYCODE_VOLUME_DOWN -> { // 볼륨을 줄였을 때
                        Toast.makeText(v.context, "Volume Down!", Toast.LENGTH_SHORT).show()
                        return@setOnKeyListener true
                    }
                }
            }
            return@setOnKeyListener false
        }
    }
}