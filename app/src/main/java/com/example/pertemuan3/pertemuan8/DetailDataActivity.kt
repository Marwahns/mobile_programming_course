package com.example.pertemuan3.pertemuan8

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.pertemuan3.R
import com.example.pertemuan3.adapter.AlumniAdapter
import com.example.pertemuan3.data.Alumni
import com.example.pertemuan3.sql.DatabaseOpenHelper

class DetailDataActivity : AppCompatActivity() {
    lateinit var sqlLiteDatabase: SQLiteDatabase
    lateinit var txtNama: TextView
    lateinit var txtTlp: TextView
    lateinit var txtPekerjaan: TextView
    lateinit var txtJurusan: TextView
    lateinit var txtTahun: TextView
    lateinit var imageButtonEdit: ImageButton
    lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data)

        txtNama = findViewById(R.id.txtNama)
        txtTlp = findViewById(R.id.txtTlp)
        txtPekerjaan = findViewById(R.id.txtPekerjaan)
        txtJurusan = findViewById(R.id.txtJurusan)
        txtTahun = findViewById(R.id.txtTahun)
        imageButtonEdit = findViewById(R.id.imageButtonEdit)
        btnDelete = findViewById(R.id.btnDelete)

        setData()

        imageButtonEdit.setOnClickListener {
            getData()
        }

        btnDelete.setOnClickListener {
            openDatabase()

            val bundle = intent.extras
            var id:Int = 0
            if(bundle!=null){
                id = bundle.getInt("id")
            }

            var delete = sqlLiteDatabase.delete("tb_alumni", "id="+id, null)

            if(delete != -1){
                Toast.makeText(this, "Successfully delete data", Toast.LENGTH_SHORT).show()
                txtNama.text = ""
                txtTlp.text = ""
                txtPekerjaan.text = ""
                txtJurusan.text = ""
                txtTahun.text = ""
                var intent = Intent(this, LihatDataActivity::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(this, "bawah", Toast.LENGTH_SHORT).show()
            }

            closeDatabase()
        }

    }

    fun getData() {
        openDatabase()
        val bundle = intent.extras
        var id = 0
        if(bundle!=null){
            id = bundle.getInt("id")
        }
        var cursor = sqlLiteDatabase.rawQuery("SELECT * FROM tb_alumni WHERE id=$id",null)

        if(cursor.moveToFirst()) {
            var alumni = Alumni()
            alumni.id = cursor.getInt(0)
            alumni.name = cursor.getString(1)
            alumni.tlp = cursor.getString(2)
            alumni.pekerjaan = cursor.getString(3)
            alumni.jurusan = cursor.getString(4)
            alumni.tahun = cursor.getString(5)
            var intent = Intent(applicationContext, UpdateDataActivity::class.java)
            intent.putExtra("id", alumni.id)
            startActivity(intent)
        }

        closeDatabase()
    }

    fun setData(){
        openDatabase()

        val bundle = intent.extras
        var id:Int = 0
        if(bundle!=null){
            id = bundle.getInt("id")
        }
        var cursor = sqlLiteDatabase.rawQuery("SELECT * FROM tb_alumni WHERE id=$id",null)

        if(cursor.moveToFirst()){
            var alumni = Alumni()
            alumni.id = cursor.getInt(0)
            alumni.name = cursor.getString(1)
            alumni.tlp = cursor.getString(2)
            alumni.pekerjaan = cursor.getString(3)
            alumni.jurusan = cursor.getString(4)
            alumni.tahun = cursor.getString(5)

            txtNama.text = alumni.name
            txtTlp.text = alumni.tlp
            txtPekerjaan.text = alumni.pekerjaan
            txtJurusan.text = alumni.jurusan
            txtTahun.text = alumni.tahun
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

