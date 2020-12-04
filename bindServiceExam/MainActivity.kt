package com.example.bindserviceexam

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var mService: MyService
    private var mBound: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as MyService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        Intent(this, MyService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }
    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    fun mOnClick(v: View) {
        when(v.id) {
            R.id.btnCalc -> {
                if(mBound) {
                    val editNum1 = findViewById<EditText>(R.id.editNum1)
                    val editNum2 = findViewById<EditText>(R.id.editNum2)
                    if(editNum1.length()>0 && editNum2.length()>0) {
                        val num1 = Integer.parseInt(editNum1.text.toString())
                        val num2 = Integer.parseInt(editNum2.text.toString())
                        val result = mService.CalcNum(num1, num2)
                        Toast.makeText(this, "계산 결과 = " + result, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}