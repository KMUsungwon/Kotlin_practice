package com.example.menuandfragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //btn1, btn2 ContextMenu 레지스터로 등록
        registerForContextMenu(findViewById(R.id.btn1));
        registerForContextMenu(findViewById(R.id.btn2));
    }

    // 메뉴 옵션을 만들어주는 함수
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu);
        val mInflater : MenuInflater = menuInflater;
        mInflater.inflate(R.menu.submenu1, menu); // 우측 상단 메뉴 내용 설정하기
        return true;
    }

    // 롱클릭으로 이벤트를 제어하는 함수 -> 기능이 작동이 안됨,,
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)


        when(v?.id) {
            R.id.btn1 -> {menu?.setHeaderTitle("배경색 변경")
                menuInflater.inflate(R.menu.submenu1, menu)}
            R.id.btn2 -> {menuInflater.inflate(R.menu.submenu2, menu)}
        }
    }

    // ContextMenu 컨트롤
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val base: LinearLayout = findViewById(R.id.baseLayout)
        val button1:Button = findViewById(R.id.btn1)
        val button2:Button = findViewById(R.id.btn2)
        when(item.itemId) {
            R.id.itemRed -> {base?.setBackgroundColor(Color.RED)}
            R.id.itemBlue -> {base?.setBackgroundColor(Color.BLUE)}
            R.id.itemGreen -> {base?.setBackgroundColor(Color.GREEN)}
            R.id.subRotate -> {button1.rotation=45F; return true;}
            R.id.subSize -> {button2.scaleX=2F; return true;}
        }

        return super.onContextItemSelected(item)
    }

    // 메뉴 옵션 컨트롤
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val base: LinearLayout = findViewById(R.id.baseLayout)
        val button1:Button = findViewById(R.id.btn1)
        val button2:Button = findViewById(R.id.btn2)
        when(item.itemId) {
            R.id.itemRed -> {base?.setBackgroundColor(Color.RED)}
            R.id.itemBlue -> {base?.setBackgroundColor(Color.BLUE)}
            R.id.itemGreen -> {base?.setBackgroundColor(Color.GREEN)}
            R.id.subRotate -> {button1.rotation=45F; return true;}
            R.id.subSize -> {button2.scaleX=2F; return true;}
        }
        return super.onOptionsItemSelected(item);
    }
}
