package com.example.fragmentexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 메인 액티비티와 프래그먼트 연결하기
        setFrag(0) //초기 프래그먼트 화면


        btn1.setOnClickListener { setFrag(0) }
        btn2.setOnClickListener { setFrag(1) }
        btn3.setOnClickListener { setFrag(2) }
    }

    private fun setFrag(fragNum : Int) {
        // 프래그먼트 매니저 -> 트랜젝션 -> 교체 -> 커밋
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum) {
            0 -> {
                ft.replace(R.id.main_frame, Fragment1()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, Fragment2()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, Fragment3()).commit()
            }
        }
    }
}