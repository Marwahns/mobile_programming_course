package com.example.pertemuan3.fragment

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pertemuan3.R
import com.example.pertemuan3.data.Alumni
import com.example.pertemuan3.sql.DatabaseOpenHelper

class DetailFragment : Fragment() {
    private lateinit var sqlLiteDatabase: SQLiteDatabase
    private lateinit var txtNama: TextView
    private lateinit var txtTlp: TextView
    private lateinit var txtPekerjaan: TextView
    private lateinit var txtJurusan: TextView
    private lateinit var txtTahun: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtNama = view.findViewById(R.id.txtNama)
        txtTlp = view.findViewById(R.id.txtTlp)
        txtPekerjaan = view.findViewById(R.id.txtPekerjaan)
        txtJurusan = view.findViewById(R.id.txtJurusan)
        txtTahun = view.findViewById(R.id.txtTahun)

        openDatabase()

        val bundle = activity?.intent?.extras
        var id = 0
        if(bundle!=null){
            id = bundle.getInt("id")
        }
        val cursor = sqlLiteDatabase.rawQuery("SELECT * FROM tb_alumni WHERE id=$id",null)

        if(cursor.moveToFirst()){
            val alumni = Alumni()
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

    private fun openDatabase(){
        sqlLiteDatabase = DatabaseOpenHelper(requireContext()).writableDatabase
    }

    private fun closeDatabase(){
        sqlLiteDatabase.close()
    }

    fun newInstance(data: Alumni): Fragment? {
        val fragmentDemo = DetailFragment()
        val args = Bundle()

        args.putString("nama", data.name)
        args.putString("tlp", data.tlp)
        args.putString("pekerjaan", data.pekerjaan)
        args.putString("jurusan", data.jurusan)
        args.putString("tahun", data.tahun)
        fragmentDemo.arguments = args
        return fragmentDemo
    }

}