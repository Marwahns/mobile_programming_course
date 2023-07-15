package com.example.pertemuan3.pertemuan5

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.pertemuan3.MainActivity
import com.example.pertemuan3.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivityPertemuan5 : AppCompatActivity(), View.OnClickListener {
    lateinit var btnDate: Button
    lateinit var btnTime: Button
    lateinit var btnHome: Button
    lateinit var btnList: Button

    lateinit var txtDate: TextView
    lateinit var txtTime: TextView

    var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pertemuan5)

        txtDate = findViewById(R.id.txt_date)
        txtTime = findViewById(R.id.txt_time)

        btnDate = findViewById(R.id.btn_picker_tanggal)
        btnTime = findViewById(R.id.btn_picker_time)
        btnHome = findViewById(R.id.btn_home)
        btnList = findViewById(R.id.btn_list)

        btnDate.setOnClickListener(this)
        btnTime.setOnClickListener(this)
        btnHome.setOnClickListener(this)
        btnList.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //v!!.id
        if(v?.id == R.id.btn_home) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        } else if(v?.id == R.id.btn_picker_tanggal){
            showDatePicker()

        } else if(v?.id == R.id.btn_picker_time){
            showTimePicker()

        } else if(v?.id == R.id.btn_list){
            val i = Intent(this, MainActivityList::class.java)
            startActivity(i)
        }
    }

    var listenerDate = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        val data = Calendar.getInstance()
        data.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        data.set(Calendar.MONTH, month)
        data.set(Calendar.YEAR, year)

        val dateFormat = SimpleDateFormat("dd MM yyyy")
        txtDate.setText((dateFormat.format(data.time)))
    }

    fun showDatePicker(){
        DatePickerDialog(this, listenerDate, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    var listenerTime = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        val data = Calendar.getInstance()
        data.set(Calendar.HOUR_OF_DAY, hourOfDay)
        data.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm")
        txtTime.setText((dateFormat.format(data.time)))
    }

    fun showTimePicker(){
        TimePickerDialog(this, listenerTime, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
    }
}