package com.example.pertemuan3.pertemuan5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pertemuan3.R
import com.example.pertemuan3.adapter.AdapterListBerita
import com.example.pertemuan3.data.DataBerita
import org.json.JSONArray

class MainActivityList : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var adatepter: AdapterListBerita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        listView = findViewById(R.id.listView)
        adatepter = AdapterListBerita(this, R.layout.item_list_layout)
        listView.adapter = adatepter
//        buatData()
        getDataPertemuan14()

        listView.setOnItemClickListener { parent, _, position, _ ->
            val data = parent.getItemAtPosition(position) as DataBerita
            // Ketika klik gambar beritanya, maka akan muncul judul berita
            Toast.makeText(applicationContext, "Judul Berita ${data.title}", Toast.LENGTH_SHORT).show()
        }
    }

    fun buatData(){
        var arrayList = ArrayList<DataBerita>()

        // var data : Array<String>
        // Datanya bisa berupa array

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

    // Pertemuan 14, 14 maksudnya 15. 15 maksudnya pertemuan 16
    fun getDataPertemuan14(){
        var arrayList = ArrayList<DataBerita>()
        // ... the RequestQueue
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.100.16/mobile_programming/backend/getdata.php"

        // Request a string response from the provided URL
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var datas = ArrayList<String>()
                val data = JSONArray(response)

                if(data.length()>0){
                    for(i in 0..data.length()-1){
                        var model = DataBerita()
                        val item = data.getJSONObject(i)
                        var id = item.getInt("id")
                        var nama = item.getString("nama")
                        var nim = item.getString("nim")
                        var kelas = item.getString("kelas")
                        var jurusan = item.getString("jurusan")
                        var semester = item.getString("semester")
                        model.title = nama
                        model.desc = nim
                        arrayList.add(model)
                    }
                }
//                Log.e("DATA : ", response)

//                var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, datas)
//                listView.adapter = arrayAdapter
                adatepter.addAll(arrayList)
                // Memberitahukan kalau ada perubahan data
                adatepter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Log.e("error", it.message!!)
            })
        // Add the request to the RequestQueue
        queue.add(stringRequest)
    }
}