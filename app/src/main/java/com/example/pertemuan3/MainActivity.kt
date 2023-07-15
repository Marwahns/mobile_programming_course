package  com.example.pertemuan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.pertemuan3.pertemuan13.ContohSMSReceiverActivity
import com.example.pertemuan3.pertemuan14.SMSReceiverActivity
import com.example.pertemuan3.pertemuan15.LocationActivity
import com.example.pertemuan3.pertemuan3.BiodataActivity
import com.example.pertemuan3.pertemuan5.MainActivityPertemuan5
import com.example.pertemuan3.pertemuan6.LatihanActivityFragment
import com.example.pertemuan3.pertemuan8.LoginActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btnPertemuan3: Button
    lateinit var btnPertemuan5: Button
    lateinit var btnPertemuan6: Button
    lateinit var btnPertemuan8: Button
    lateinit var btnPertemuan13: Button
    lateinit var btnPertemuan14: Button
    lateinit var btnPertemuan15: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPertemuan3 = findViewById(R.id.BtnPertemuan3)
        btnPertemuan5 = findViewById(R.id.BtnPertemuan5)
        btnPertemuan6 = findViewById(R.id.BtnPertemuan6)
        btnPertemuan8 = findViewById(R.id.BtnPertemuan8)
        btnPertemuan13 = findViewById(R.id.BtnPertemuan13)
        btnPertemuan14 = findViewById(R.id.BtnPertemuan14)
        btnPertemuan15 = findViewById(R.id.BtnPertemuan15)

        btnPertemuan3.setOnClickListener(this)
        btnPertemuan5.setOnClickListener(this)
        btnPertemuan6.setOnClickListener(this)
        btnPertemuan8.setOnClickListener(this)
        btnPertemuan13.setOnClickListener(this)
        btnPertemuan14.setOnClickListener(this)
        btnPertemuan15.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        Log.e("Apa benar ini TAG?", "onStart: ini print onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Apa benar ini TAG?", "onResume: ini print onResume")
    }

    override fun onClick(v: View?) {
        //v!!.id
        if(v?.id ==R.id.BtnPertemuan3) {
            val i = Intent(this, BiodataActivity::class.java)
            i.putExtra("nama", "Marwah Nur Shafira")
            i.putExtra("umur", 18)
            startActivity(i);
        } else if (v?.id ==R.id.BtnPertemuan5){
            val i = Intent(this, MainActivityPertemuan5::class.java)
            startActivity(i)
        } else if (v?.id ==R.id.BtnPertemuan6) {
            val i = Intent(this, LatihanActivityFragment::class.java)
            startActivity(i)
        } else if (v?.id ==R.id.BtnPertemuan8) {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        } else if (v?.id ==R.id.BtnPertemuan13) {
            val i = Intent(this, ContohSMSReceiverActivity::class.java)
            startActivity(i)
        } else if (v?.id ==R.id.BtnPertemuan14) {
            val i = Intent(this, SMSReceiverActivity::class.java)
            startActivity(i)
        } else if (v?.id ==R.id.BtnPertemuan15) {
            val i = Intent(this, LocationActivity::class.java)
            startActivity(i)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100){
            btnPertemuan3.setText(data?.extras?.getString("data"))
        } else{

        }
    }

}