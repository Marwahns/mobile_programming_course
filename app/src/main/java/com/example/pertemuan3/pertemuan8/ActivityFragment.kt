package com.example.pertemuan3.pertemuan8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pertemuan3.R
import com.example.pertemuan3.data.Alumni
import com.example.pertemuan3.data.DataBerita
import com.example.pertemuan3.fragment.DetailFragment
import com.example.pertemuan3.fragment.FragmentOne
import com.example.pertemuan3.fragment.FragmentTwo
import com.example.pertemuan3.fragment.ListFragment

class ActivityFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        var fm = supportFragmentManager.beginTransaction()
        fm.add(R.id.containerLayout, ListFragment())
        fm.commit()
    }

    fun pindahHalamanDetail(data: Alumni){
        var halamanDetail = DetailFragment()
        var bundle = Bundle()

        halamanDetail.arguments = bundle

        var fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.containerLayout, DetailFragment()?.newInstance(data)!!)

        // Memberikan value untuk berpindah halaman
        // addToBackStack = agar ketika berpindah kehalaman sebelumnya, maka tidak langsung keluar dari aplikasi. Tetapi akan kembali ke fragment sebelumnya
        fm.addToBackStack(null)
        fm.commit()

        Log.e("E", "pindah")
    }
}