package com.example.pertemuan3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pertemuan3.R
import com.example.pertemuan3.data.DataBerita

class AdapterListBerita(context: Context, resource: Int):
    ArrayAdapter<DataBerita>(context, resource) {

    var _context = context
    var _resource = resource

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Update isi-isi dari item
        var holder:Holder
        lateinit var viewItem: View

        if(convertView==null){
            holder = Holder()
            viewItem = LayoutInflater.from(_context).inflate(_resource, null, false)
            holder.imageView = viewItem.findViewById(R.id.imageView)
            holder.txtTitle = viewItem.findViewById(R.id.txtTitle)
            holder.txtDesc = viewItem.findViewById(R.id.txtDesc)

            viewItem.setTag(holder)

        }else{
            holder = convertView.getTag() as Holder
            viewItem = convertView
        }

        holder.txtTitle.setText(getItem(position)?.title)
        holder.txtDesc.setText(getItem(position)?.desc)
        Glide.with(_context).load(getItem(position)?.imageUrl).into(holder.imageView)

        return viewItem
    }
}

class Holder {
    lateinit var imageView: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtDesc: TextView
}