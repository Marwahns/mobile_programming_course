package com.example.pertemuan3.pertemuan8

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.pertemuan3.R
import com.example.pertemuan3.adapter.AlumniAdapter
import com.example.pertemuan3.data.Alumni
import com.example.pertemuan3.sql.DatabaseOpenHelper

class LihatDataActivity : AppCompatActivity() {
    // lateinit var => inisialisasi datanya nanti. diisinya nanti
    lateinit var sqlLiteDatabase: SQLiteDatabase
    lateinit var listView: ListView
    lateinit var adapter: AlumniAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data)
        listView = findViewById(R.id.listView)
        adapter = AlumniAdapter(this, R.layout.layout_item)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            var alumni = parent.adapter.getItem(position) as Alumni
//            var intent = Intent(applicationContext, UpdateDataActivity::class.java)
            var intent = Intent(applicationContext, DetailDataActivity::class.java)
            intent.putExtra("id", alumni.id)
            startActivity(intent)
        }

        getData()
    }

    // on resume itu untuk refresh terus data
    override fun onResume() {
        super.onResume()
        getData()
    }

    fun getData(){
        openDatabase()
        var cursor = sqlLiteDatabase.rawQuery("SELECT * FROM tb_alumni", null)
        var data = ArrayList<Alumni>()
        if(cursor.moveToFirst()){
            do{
                var alumni = Alumni()
                alumni.id = cursor.getInt(0)
                alumni.name = cursor.getString(1)
                alumni.tlp = cursor.getString(2)
                alumni.pekerjaan = cursor.getString(3)
                alumni.jurusan = cursor.getString(4)
                alumni.tahun = cursor.getString(5)
                data.add(alumni)

            } while (cursor.moveToNext())

            adapter.clear()
            adapter.addAll(data)
            adapter.notifyDataSetChanged()
        }
        closeDatabase()
    }

    fun openDatabase(){
        sqlLiteDatabase = DatabaseOpenHelper(this).writableDatabase
    }

    fun closeDatabase(){
        sqlLiteDatabase.close()
    }
}