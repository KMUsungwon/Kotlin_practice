package com.example.fileio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var mEditName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditName = findViewById(R.id.editName)

    }

    fun mOnClick(v: View) {
        when (v.id) {
            R.id.btnSave -> {
                var fos: FileOutputStream // 파일 쓰기
                try {
                    fos = openFileOutput("test.txt", Context.MODE_PRIVATE) // test.txt 라는 파일을 생성하여 파일 쓰기
                    fos.write(mEditName.text.toString().toByteArray()) // byteArray 형태로 값을 저장
                    fos.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            R.id.btnLoad -> {
                var fis: FileInputStream
                try {
                    fis = openFileInput("test.txt") // test.txt 파일의 값을 읽어옴
                    var content = fis.readBytes().toString(Charset.defaultCharset()) // 바이트 단위로 읽어서 문자열로 변환
                    Toast.makeText(this, content, Toast.LENGTH_SHORT).show() // 값 출력이 제대로 되는지 확인해보기 위한 메시지
                    mEditName.setText(content) // 텍스트 파일에서 읽어온 값을 TextView 에 저장
                    fis.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}