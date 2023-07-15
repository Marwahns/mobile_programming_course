package com.example.pertemuan3.pertemuan13

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pertemuan3.MessageListener
import com.example.pertemuan3.R
import com.example.pertemuan3.receiver.MessageReceiver
import org.json.JSONArray

class ContohSMSReceiverActivity : AppCompatActivity(), MessageListener {
    lateinit var buttonKirim: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contoh_smsreceiver)
        buttonKirim = findViewById(R.id.btn_kirim)
        requestPermission()

        MessageReceiver.bindListener(this)
//        getData()

        buttonKirim.setOnClickListener {
//            sendSmsByManager()
            sendSmsBySIntent()
        }
    }

    fun requestPermission(){
        var strPermission1 = android.Manifest.permission.RECEIVE_SMS
        var strPermission2 = android.Manifest.permission.SEND_SMS
        var grant1 = ContextCompat.checkSelfPermission(this, strPermission1)
        var grant2 = ContextCompat.checkSelfPermission(this, strPermission2)
        if(grant1 != PackageManager.PERMISSION_GRANTED && grant2 != PackageManager.PERMISSION_GRANTED){
            var permissions = arrayOf(strPermission1, strPermission2)
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }

    override fun messageReceived(message: String?){
        // update ui

        Log.e("Pesan Masuk : ", "Pesan * "+ message+" *")
    }

    fun sendSmsByManager(){
        try {
            // Mengambil default instance dari SmsManager
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(
                "1234567890",
                null,
                "Hallo Apa Kabar",
                null,
                null
            )
            Toast.makeText(
                applicationContext, "SMS Berhasil Dikirim!",
                Toast.LENGTH_SHORT
            ).show()
        } catch (ex: Exception){
            Toast.makeText(
                applicationContext, "Pengiriman SMS Gagal : " +ex.message,
                Toast.LENGTH_SHORT
            ).show()
            ex.printStackTrace()
        }
    }

    fun sendSmsBySIntent(){
        // menambahkan phone number ke URI data
        var uri: Uri = Uri.parse("smsto:1234567890")

        // membuat intent baru dengan ACTION_SENDTO
        val smsSIntent = Intent(Intent.ACTION_SENDTO, uri)

        // menambahkan isi SMS pada field sms_body
        smsSIntent.putExtra("sms_body", "Halo Apa Kabar")
        try{
            startActivity(smsSIntent)
        } catch (ex: Exception){
            Toast.makeText(
                this@ContohSMSReceiverActivity, "Pengiriman SMS Gagal...",
                Toast.LENGTH_LONG
            ).show()
            ex.printStackTrace()
        }
    }

    // Pertemuan 14
//    fun getData(){
//        // ... the RequestQueue
//        val queue = Volley.newRequestQueue(this)
//        val url = "http://10.10.14.234/latihanbackend/getdata.php"
//
//        // Request a string response from the provided URL
//        val stringRequest = StringRequest(Request.Method.GET, url,
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
//            },
//            Response.ErrorListener {
//                Log.e("error", it.message!!)
//            })
//        // Add the request to the RequestQueue
//        queue.add(stringRequest)
//    }
}