package com.example.pertemuan3.pertemuan7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.pertemuan3.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

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