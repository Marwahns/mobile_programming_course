package com.example.pertemuan3.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(
    context: Context
) : SQLiteOpenHelper(context, "db_alumni", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE tb_alumni (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, no_tlp TEXT, " +
                "pekerjaan TEXT, jurusan TEXT, tahun INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}

