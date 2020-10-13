package com.example.a20201013intent

import android.app.SearchManager
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("암시적 인텐트 예제")

        val btnDial: Button = findViewById(R.id.btnDial) // 전화번호 값 다이얼로 보내기
        val btnWeb: Button = findViewById(R.id.btnWeb) // 링크 주소로 이동하기
        val btnSearch: Button = findViewById(R.id.btnSearch) //구글 서치

        // 다이얼 화면으로 이동하기
        btnDial.setOnClickListener {
            val uri: Uri = Uri.parse("tel:010-7545-5896")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }

        // 입력한 주소로 이동하기
        btnWeb.setOnClickListener {
            val uri: Uri = Uri.parse("https://www.naver.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        // 구글 서치
        btnSearch.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, "코틀린 파이어베이스") //key, value 순서 key는 query가 된다.
            startActivity(intent)
        }
    }

}