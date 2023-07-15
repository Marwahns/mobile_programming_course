package com.example.pertemuan3.fragment

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pertemuan3.R
import com.example.pertemuan3.adapter.AdapterListBerita
import com.example.pertemuan3.data.DataBerita
import com.example.pertemuan3.pertemuan6.LatihanActivityFragment

class FragmentOne : Fragment() {
    lateinit var listView: ListView
    lateinit var adatepter: AdapterListBerita

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.listView)
        adatepter = AdapterListBerita(requireActivity(), R.layout.item_list_layout)
        listView.adapter = adatepter
        buatData()

        listView.setOnItemClickListener(AdapterView.OnItemClickListener{ parent, view, position, id ->
            var data = parent.getItemAtPosition(position) as DataBerita
            (requireActivity() as LatihanActivityFragment).pindahHalamanDetail(data)
        })

        // pertemuan 7
        // ketika listviewnya di press
        registerForContextMenu(listView)
    }

    // pertemuan 7
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // inflater ada di dalam activity
        // inflater
        var menuInflater = requireActivity().menuInflater
        menuInflater.inflate(R.menu.optionmenu,menu)
    }

    // pertemuan 7
    override fun onContextItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.m_about){
            Toast.makeText(requireActivity(), "About", Toast.LENGTH_SHORT).show()
        } else if(item.itemId==R.id.m_setting){
            Toast.makeText(requireActivity(), "Setting", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireActivity(), "Help", Toast.LENGTH_SHORT).show()
        }

        return super.onContextItemSelected(item)
    }

    fun buatData(){
        var arrayList = ArrayList<DataBerita>()

        for (i in 0..10){
            var data1 = DataBerita()
            data1.imageUrl = "https://akcdn.detik.net.id/community/media/visual/2019/05/08/31517792-b8d3-4a71-b544-8dd3c1d31a27_169.jpeg?w=700&q=90"
            data1.title = "Berita $i"
            data1.desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            arrayList.add(data1)
        }

        adatepter.addAll(arrayList)

        // Memberitahukan kalau ada perubahan data
        adatepter.notifyDataSetChanged()
    }
}