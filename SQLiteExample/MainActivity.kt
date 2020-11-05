package com.example.a20201105

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var myDBHelper: MyDBHelper
    lateinit var mEditName: EditText
    lateinit var mEditAge: EditText
    lateinit var mTextResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDBHelper = MyDBHelper(this)
        mEditName = findViewById(R.id.editName)
        mEditAge = findViewById(R.id.editAge)
        mTextResult = findViewById(R.id.textResult)
    }

    fun mOnClick(view: View) {
        var db: SQLiteDatabase
        var values: ContentValues
        val projection = arrayOf("_id","name","age")
        var cur: Cursor

        when(view.id) {
            // 삽입
            R.id.btnInsert -> {
                if(mEditName.text.length > 0 && mEditAge.text.length > 0) {
                    db = myDBHelper.writableDatabase
                    values = ContentValues()
                    values.put("name", mEditName.text.toString())
                    values.put("age", mEditAge.text.toString())
                    db.insert("people", null, values)
                    myDBHelper.close()
                }
            }
            //전체 검색
            R.id.btnSelectAll -> {
                db = myDBHelper.readableDatabase
                cur = db.query("people", projection, null, null, null, null, null)
                if(cur != null) {
                    showResult(cur)
                    cur.close()
                }
                myDBHelper.close()
            }
            // 이름 검색
            R.id.btnSelectName -> {
                if(mEditName.text.length > 0) {
                    db = myDBHelper.readableDatabase
                    val name = mEditName.text.toString()
                    cur = db.query("people", projection, "name=?", arrayOf(name), null, null, null)
                    if(cur != null) {
                        showResult(cur)
                        cur.close()
                    }
                    myDBHelper.close()
                }
            }
            //나이 갱신
            R.id.btnUpdateAge -> {
                if(mEditName.text.length >0 && mEditAge.text.length > 0 ) {
                    db = myDBHelper.writableDatabase
                    val name = mEditName.text.toString()
                    values = ContentValues()
                    values.put("age",mEditAge.text.toString())
                    db.update("people", values, "name=?", arrayOf(name))
                    myDBHelper.close()
                }
            }
            // 이름 삭제
            R.id.btnDeleteName -> {
                if(mEditName.text.length >0) {
                    db = myDBHelper.writableDatabase
                    val name = mEditName.text.toString()
                    db.delete("people", "name=?", arrayOf(name))
                    myDBHelper.close()
                }
            }
            R.id.btnDeleteAll -> {
                db = myDBHelper.writableDatabase
                db.delete("people", null, null)
                myDBHelper.close()
            }
        }
    }
    private fun showResult(cur: Cursor) {
        mTextResult.text = ""
        var name_col = cur.getColumnIndex("name")
        var age_col = cur.getColumnIndex("age")
        while(cur.moveToNext()) {
            var name = cur.getString(name_col)
            var age = cur.getString(age_col)
            mTextResult.append(name + ", " + age + "\n")
        }
    }
}

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "testDB",null,1)  {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE people(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, age TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXiSTS people")
        onCreate(db)
    }

}