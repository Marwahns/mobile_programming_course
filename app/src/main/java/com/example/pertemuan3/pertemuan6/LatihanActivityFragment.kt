package com.example.pertemuan3.pertemuan6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.pertemuan3.R
import com.example.pertemuan3.data.DataBerita
import com.example.pertemuan3.fragment.FragmentOne
import com.example.pertemuan3.fragment.FragmentTwo

//Fragment Statis
class LatihanActivityFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan_fragment)

        var sharedPreferences = getSharedPreferences("dataku", MODE_PRIVATE)

        var editor = sharedPreferences.edit()
        editor.putString("nama", "Marwah Nur Shafira")

        // commit untuk simpa, clear untuk hapus
        editor.commit()

        var fm = supportFragmentManager.beginTransaction()
        fm.add(R.id.containerLayout, FragmentOne())
        fm.commit()
    }

    fun pindahHalamanDetail(data:DataBerita){
        var halamanDetail = FragmentTwo()
        var bundle = Bundle()

        halamanDetail.arguments = bundle

        var fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.containerLayout, FragmentTwo()?.newInstance(data)!!)

        // Memberikan value untuk berpindah halaman
        // addToBackStack = agar ketika berpindah kehalaman sebelumnya, maka tidak langsung keluar dari aplikasi. Tetapi akan kembali ke fragment sebelumnya
        fm.addToBackStack(null)
        fm.commit()

        Log.e("E", "pindah")
    }

//    pertemuan 7
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = menuInflater
        menuInflater.inflate(R.menu.optionmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.m_about){
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
        } else if(item.itemId==R.id.m_setting){
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
        } else if(item.itemId==R.id.m_help){
            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Tambah Data", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}