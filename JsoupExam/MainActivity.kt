package com.example.jsoupexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.jsoup.Jsoup
import org.w3c.dom.Text
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = findViewById<TextView>(R.id.title)
        var aList = arrayListOf<String>()

        thread {
            // url 링크 가져오기
            var doc = Jsoup.connect("https://datalab.naver.com/keyword/realtimeList.naver?where=main").get()
            val elements = doc.title() // 해당 페이지 제목 가져오기
            title.text = elements

            // item_title_wrap 클래스의 텍스트 추출하여 배열에 추가
            var subject = doc.getElementsByClass("item_title_wrap")
            for(i in 0..19) {
                aList.add(subject[i].text())
            }

            Log.d("급상승 검색어 목록", aList.toString())
        }
    }
}