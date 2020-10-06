package com.example.senddata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second Activity");

        val inIntent: Intent = getIntent();
        val hapValue = inIntent.getIntExtra("Num1",0) + inIntent.getIntExtra("Num2",0);

        val btnReturn: Button = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener {
            val outIntent: Intent = Intent(this,MainActivity::class.java);
            outIntent.putExtra("Hap",hapValue);
            setResult(RESULT_OK, outIntent);
            finish();
        }
    }
}