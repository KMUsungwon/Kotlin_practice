package com.example.senddata

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        setTitle("First Activity");

        val btnNewButton: Button = findViewById(R.id.btnNewActivity);

        btnNewButton.setOnClickListener {
            val num1: EditText = findViewById(R.id.value1);
            val num2: EditText = findViewById(R.id.value2);

            val intent = Intent(this, SecondActivity::class.java);
            intent.putExtra("Num1", Integer.parseInt(num1.text.toString()));
            intent.putExtra("Num2", Integer.parseInt(num2.text.toString()));
            startActivityForResult(intent, 0);
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) run {
            val hap = data?.getIntExtra("Hap", 0);
            Toast.makeText(this, "합계 :" + hap, Toast.LENGTH_SHORT).show();
        }
    }
}
