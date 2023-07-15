package com.example.pertemuan3.pertemuan14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pertemuan3.R
import org.json.JSONArray

class SMSReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smsreceiver)
//        getData()
    }

//    fun getData(){
//        // ... the RequestQueue
//        val queue = Volley.newRequestQueue(this)
//        val url = "http://192.168.100.16/mobile_programming/backend/getdata.php"
//
//        // Request a string response from the provided URL
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            Response.Listener<String> { response ->
//                var datas = ArrayList<String>()
//                val data = JSONArray(response)
//
//                if(data.length()>0){
//                    for(i in 0..data.length()){
//                        val item = data.getJSONObject(i)
//                        var id = item.getString("id")
//                        var nama = item.getString("nama")
//                        var nim = item.getString("nim")
//                        var kelas = item.getString("kelas")
//                        var jurusan = item.getString("jurusan")
//                        var semester = item.getString("semester")
//                    }
//                }
////                Log.e("DATA : ", response)
//
//                var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, datas)
//                listView.adapter = arrayAdapter
//            },
//            Response.ErrorListener {
//                Log.e("error", it.message!!)
//            })
//        // Add the request to the RequestQueue
//        queue.add(stringRequest)
//    }

}