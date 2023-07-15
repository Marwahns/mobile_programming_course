package com.example.pertemuan3.fragment

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.pertemuan3.R
import com.example.pertemuan3.adapter.AdapterListBerita
import com.example.pertemuan3.adapter.AlumniAdapter
import com.example.pertemuan3.data.Alumni
import com.example.pertemuan3.data.DataBerita
import com.example.pertemuan3.pertemuan6.LatihanActivityFragment
import com.example.pertemuan3.pertemuan8.ActivityFragment
import com.example.pertemuan3.sql.DatabaseOpenHelper

class ListFragment : Fragment() {
    lateinit var sqlLiteDatabase: SQLiteDatabase
    lateinit var listView: ListView
    lateinit var adatepter: AlumniAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.listView)
        adatepter = AlumniAdapter(requireActivity(), R.layout.fragment_list)
        listView.adapter = adatepter
        buatData()

        listView.setOnItemClickListener(AdapterView.OnItemClickListener{ parent, view, position, id ->
            var alumni = parent.getItemAtPosition(position) as Alumni
            (requireActivity() as ActivityFragment).pindahHalamanDetail(alumni)
        })

        // ketika listviewnya di press
        registerForContextMenu(listView)
    }

    fun buatData(){
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

            adatepter.clear()
            adatepter.addAll(data)
            adatepter.notifyDataSetChanged()
        }
        closeDatabase()

    }

    fun openDatabase(){
        sqlLiteDatabase = DatabaseOpenHelper(requireContext()).writableDatabase
    }

    fun closeDatabase(){
        sqlLiteDatabase.close()
    }
}