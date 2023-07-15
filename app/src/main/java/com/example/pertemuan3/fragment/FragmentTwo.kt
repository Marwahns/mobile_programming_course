package com.example.pertemuan3.fragment

import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pertemuan3.R
import com.example.pertemuan3.data.DataBerita

class FragmentTwo : Fragment() {
    lateinit var  imageView: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtDesc: TextView

    fun newInstance(data:DataBerita): FragmentTwo? {
        val fragmentDemo = FragmentTwo()
        val args = Bundle()

        args.putString("title", data.title)
        args.putString("img", data.imageUrl)
        args.putString("desc", data.desc)
        // fragmentDemo.setArguments = args
        fragmentDemo.arguments = args
        return fragmentDemo
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.layout_fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.imageView)
        txtTitle = view.findViewById(R.id.txtTitle)
        txtDesc = view.findViewById(R.id.txtDesc)

        var bundle = arguments

        Glide.with(requireActivity()).load(bundle?.getString("img","")).into(imageView)

        // pertemuan 7
        var sharedPreferences = requireActivity().getSharedPreferences("dataku", Context.MODE_PRIVATE)

//        txtTitle.setText(bundle?.getString("title", ""))
        txtTitle.setText(sharedPreferences.getString("nama", ""))
        txtDesc.setText(bundle?.getString("desc", ""))
    }
}