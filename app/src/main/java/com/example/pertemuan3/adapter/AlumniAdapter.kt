package com.example.pertemuan3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pertemuan3.R
import com.example.pertemuan3.data.Alumni

class AlumniAdapter(context: Context, resouce:Int) : ArrayAdapter<Alumni>(context, resouce){
//    lateinit var context: Context

    var _context = context // ide sendiri
    var resouce: Int = 0
    init{
        this._context = context
        this.resouce = resouce
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder:Holder
        var viewItemData = convertView

        try {
            if(convertView==null){
                holder = Holder()

                viewItemData = LayoutInflater.from(context).inflate(resouce,parent,false)
                holder.txtNama = viewItemData.findViewById(R.id.txtNim)
                holder.txtNama = viewItemData.findViewById(R.id.txtNama)
//                holder.txtTlp = viewItemData.findViewById(R.id.txtNoTlp)
//                holder.txtPekerjaan = viewItemData.findViewById(R.id.txtPekerjaan)
//                holder.txtJurusan = viewItemData.findViewById(R.id.txtJurusan)
//                holder.txtTahun = viewItemData.findViewById(R.id.txtTahun)

            } else{
                holder = viewItemData?.tag as Holder
                viewItemData = convertView
            }

            // seharusnya val data
            var data = getItem(position)

            holder.txtNim.setText(data?.nim)
            holder.txtNama.setText(data?.name)
            holder.txtTlp.setText(data?.tlp)
            holder.txtPekerjaan.setText(data?.pekerjaan)
            holder.txtJurusan.setText(data?.jurusan)
//        holder.txtTahun.setText(data?.tahun)
            holder.txtTahun.setText(""+data?.tahun)
        } catch (e:Exception){

        }
        return viewItemData!!
    }

    class Holder {
        lateinit var txtNim:TextView
        lateinit var txtNama:TextView
        lateinit var txtTlp:TextView
        lateinit var txtPekerjaan:TextView
        lateinit var txtJurusan:TextView
        lateinit var txtTahun:TextView
    }
}