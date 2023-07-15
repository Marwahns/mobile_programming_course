package com.example.pertemuan3.pertemuan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.pertemuan3.MainActivity
import com.example.pertemuan3.R

class BiodataActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btnHome: Button

    lateinit var txtNama:TextView
    lateinit var txtUmur:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata)

        val bundle = intent.extras
        txtNama = findViewById(R.id.txtNama)
        txtUmur = findViewById(R.id.txtUmur)

        btnHome = findViewById(R.id.BtnHome)

        btnHome.setOnClickListener(this)

        if(bundle!=null){
            // key nya diambil dari 'name' yang ada di MainActivity
            val nama = bundle.getString("nama")
            val umur = bundle.getInt("umur")
            txtNama.setText(nama)
            // karna integer harus di concate
            txtUmur.setText(""+umur)
        }
    }

    override fun onClick(v: View?) {
        //v!!.id
        if(v?.id == R.id.BtnHome) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i);
        }
    }
}