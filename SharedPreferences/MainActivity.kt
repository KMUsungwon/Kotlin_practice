package com.example.a20201029

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

//Shared Preferences Example
class MainActivity : AppCompatActivity() {
    private lateinit var mEditName: EditText
    private lateinit var mEditAge: EditText
    private lateinit var mCheckMan: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditName = findViewById(R.id.editName)
        mEditAge = findViewById(R.id.editAge)
        mCheckMan = findViewById(R.id.checkMan)

        if(savedInstanceState == null) {
            val prefs = getSharedPreferences("person_info", 0) //person_info xml 파일 생성

            //person_info.xml에 저장할 키값 생성
            val name = prefs.getString("name","")
            val age = prefs.getString("age","")
            val man = prefs.getBoolean("man",false)

            mEditName.setText(name)
            mEditAge.setText(age)
            mCheckMan.isChecked = man
        }
    }

    //해당 액티비티(Page)를 벗어나야 onDestroy 함수가 실행된다.
    override fun onDestroy() {
        super.onDestroy()

        val prefs = getSharedPreferences("person_info",0)
        val editor = prefs.edit()

        val name = mEditName.text.toString()
        val age = Integer.parseInt(mEditAge.text.toString())
        val man = mCheckMan.isChecked

        editor.putString("name",name)
        editor.putInt("age",age)
        editor.putBoolean("man",man)
        editor.apply() // 데이터를 person_info.xml 파일에 commit 한다고 생각하면 편함
    }
    // person_info.xml 참조하는 방법
    //Device File Explorer -> data -> data -> searching package name (a20201029)
}
