package com.example.pertemuan3.pertemuan8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.pertemuan3.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menu = menuInflater.inflate(R.menu.menu_app, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_tambah){
            var intent = Intent(this, TambahDataActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.menu_lihat){
            var intent = Intent(this, LihatDataActivity::class.java)
            startActivity(intent)
        } else {
            var data = getSharedPreferences("dataLogin", Context.MODE_PRIVATE)
            var edit = data.edit()
            edit.clear()
            edit.commit()
            finish()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}