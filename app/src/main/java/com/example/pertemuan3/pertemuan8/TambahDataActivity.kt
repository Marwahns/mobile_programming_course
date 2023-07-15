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

class TambahDataActivity : AppCompatActivity() {
    lateinit var sqlLiteDatabase: SQLiteDatabase
    lateinit var edtNim:EditText
    lateinit var edtNama:EditText
    lateinit var edtNoTlp:EditText
    lateinit var edtPekerjaan:EditText
    lateinit var edtJurusan:EditText
    lateinit var edtTahun:EditText
    lateinit var btnSimpan:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)

        edtNim = findViewById(R.id.edtNim)
        edtNama = findViewById(R.id.edtNama)
        edtNoTlp = findViewById(R.id.edtNoTlp)
        edtPekerjaan = findViewById(R.id.edtPekerjaan)
        edtJurusan = findViewById(R.id.edtJurusan)
        edtTahun = findViewById(R.id.edtTahun)
        btnSimpan = findViewById(R.id.btnSimpan)

        btnSimpan.setOnClickListener{
            if(!edtNama.text.isEmpty() || !edtNoTlp.text.isEmpty() || !edtPekerjaan.text.isEmpty() ||
                !edtJurusan.text.isEmpty() || !edtTahun.text.isEmpty()) {

                openDatabase()
                // masukkann pekerjaan dan bekerja dimana?
                var contentValue = ContentValues()
                contentValue.put("nim", edtNim.text.toString())
                contentValue.put("nama", edtNama.text.toString())
                contentValue.put("no_tlp", edtNoTlp.text.toString())
                contentValue.put("pekerjaan", edtPekerjaan.text.toString())
                contentValue.put("jurusan", edtJurusan.text.toString())
                contentValue.put("tahun", edtTahun.text.toString())

                var insert:Long= sqlLiteDatabase.insert("tb_alumni", null, contentValue)

                if(!insert.equals(-1)){
                    Toast.makeText(this, "atas", Toast.LENGTH_SHORT).show()
                    edtNim.setText("")
                    edtNama.setText("")
                    edtNoTlp.setText("")
                    edtPekerjaan.setText("")
                    edtJurusan.setText("")
                    edtTahun.setText("")
                    var intent = Intent(this, LihatDataActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "bawah", Toast.LENGTH_SHORT).show()
                }

                closeDatabase()

            }
        }
    }

    fun openDatabase(){
        sqlLiteDatabase = DatabaseOpenHelper(this).writableDatabase
    }

    fun closeDatabase(){
        sqlLiteDatabase.close()
    }
}