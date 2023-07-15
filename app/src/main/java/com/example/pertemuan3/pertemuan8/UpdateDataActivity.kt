package com.example.pertemuan3.pertemuan8

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pertemuan3.R
import com.example.pertemuan3.data.Alumni
import com.example.pertemuan3.sql.DatabaseOpenHelper

class UpdateDataActivity : AppCompatActivity() {
    lateinit var sqlLiteDatabase: SQLiteDatabase
    lateinit var edtNama:EditText
    lateinit var edtNoTlp:EditText
    lateinit var edtPekerjaan:EditText
    lateinit var edtJurusan:EditText
    lateinit var edtTahun:EditText
    lateinit var btnSimpan:Button
//    lateinit var btnHapus:Button
//    lateinit var btnBack:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)

        edtNama = findViewById(R.id.edtNama)
        edtNoTlp = findViewById(R.id.edtNoTlp)
        edtPekerjaan = findViewById(R.id.edtPekerjaan)
        edtJurusan = findViewById(R.id.edtJurusan)
        edtTahun = findViewById(R.id.edtTahun)
        btnSimpan = findViewById(R.id.btnSimpan)

        setData()

        // nanti disini datanya diupdate

        btnSimpan.setOnClickListener{
            if(!edtNama.text.isEmpty() || !edtNoTlp.text.isEmpty() || !edtPekerjaan.text.isEmpty() ||
                !edtJurusan.text.isEmpty() || !edtTahun.text.isEmpty()) {

                openDatabase()

                val bundle = intent.extras
                var id:Int = 0
                if(bundle!=null){
                    id = bundle.getInt("id")
                }

                var contentValue = ContentValues()
                contentValue.put("nama", edtNama.text.toString())
                contentValue.put("no_tlp", edtNoTlp.text.toString())
                contentValue.put("pekerjaan", edtPekerjaan.text.toString())
                contentValue.put("jurusan", edtJurusan.text.toString())
                contentValue.put("tahun", edtTahun.text.toString())

                var update = sqlLiteDatabase.update("tb_alumni", contentValue, "id="+id, null)

                if(!update.equals(-1)){
                    Toast.makeText(this, "Simpan Data Berhasil", Toast.LENGTH_SHORT).show()
//                    edtNama.setText("")
//                    edtNoTlp.setText("")
//                    edtPekerjaan.setText("")
//                    edtJurusan.setText("")
//                    edtTahun.setText("")
                    var intent = Intent(this, LihatDataActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "Simpan Data Berhasil", Toast.LENGTH_SHORT).show()
                }

                closeDatabase()

            }
        }

    }

    fun setData(){
        openDatabase()

        val bundle = intent.extras
        var id:Int = 0
        if(bundle!=null){
            id = bundle.getInt("id")
        }
        var cursor = sqlLiteDatabase.rawQuery("SELECT * FROM tb_alumni WHERE id=$id",null)
        var data = ArrayList<Alumni>()

        if(cursor.moveToFirst()){
            var alumni = Alumni()
            alumni.id = cursor.getInt(0)
            alumni.name = cursor.getString(1)
            alumni.tlp = cursor.getString(2)
            alumni.pekerjaan = cursor.getString(3)
            alumni.jurusan = cursor.getString(4)
            alumni.tahun = cursor.getString(5)

            edtNama.setText(alumni.name)
            edtNoTlp.setText(alumni.tlp)
            edtPekerjaan.setText(alumni.pekerjaan)
            edtJurusan.setText(alumni.jurusan)
            edtTahun.setText(alumni.tahun)

//            edtNama.isEnabled = false
//            edtNoTlp.isEnabled = false
//            edtPekerjaan.isEnabled = false
//            edtJurusan.isEnabled = false
//            edtTahun.isEnabled = false
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