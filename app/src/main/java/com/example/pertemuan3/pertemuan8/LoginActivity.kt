package com.example.pertemuan3.pertemuan8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.pertemuan3.R

class LoginActivity : AppCompatActivity() {
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var showPassword: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //agar data dapat diakses di aplikasi lain
        var data = getSharedPreferences("dataLogin", MODE_PRIVATE)

        if(data.getBoolean("isLogin",false)){
            finish()
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        showPassword = findViewById(R.id.showPassword)


        if (edtEmail.text.toString() == "" || edtPassword.text.toString() == ""){
            btnLogin.setBackgroundColor(resources.getColor(R.color.login_email_invalid))
            btnLogin.setTextColor(resources.getColor(R.color.white))
            btnLogin.isEnabled = false
        }

        edtEmail.doOnTextChanged { text, start, before, count ->
            if (Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()) {
                edtEmail.setBackgroundResource(R.drawable.rounded_border_edit_text)
                btnLogin.setBackgroundColor(resources.getColor(R.color.login))
                btnLogin.setTextColor(resources.getColor(R.color.white))
                btnLogin.isEnabled = true
            } else {
                edtEmail.setBackgroundResource(R.drawable.rounded_warning_border_edit_text)
                btnLogin.setBackgroundColor(resources.getColor(R.color.login_email_invalid))
                btnLogin.setTextColor(resources.getColor(R.color.white))
                btnLogin.isEnabled = false
                edtEmail.error = "Invalid Email Address"
            }
        }

        // Show Hide Password
        showPassword.setImageResource(R.drawable.baseline_lock_24)
        showPassword.setOnClickListener(View.OnClickListener {
            if (edtPassword.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())){
                // If password is visible then Hide it
                edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                // Change icon
                showPassword.setImageResource(R.drawable.baseline_lock_24)
            } else{
                edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showPassword.setImageResource(R.drawable.baseline_lock_open_24)
            }
        })

        // context = posisi asal halaman itu dipanggil

        btnLogin.setOnClickListener {
            if (edtEmail.text.toString() == "a@gmail.com" && edtPassword.text.toString() == "123") {
                var edit = data.edit()
                edit.putString("nama", "Marwah Nur Shafira")
                edit.putString("email", "marwahnurshafira1705@gmail.com")
                edit.putString("nim", "2107411008")
                edit.putBoolean("isLogin", true)
                edit.commit()
                finish()
                var intent = Intent(this, HomeActivity::class.java)
                Toast.makeText(applicationContext, "Login berhasil", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else if (edtEmail.text.toString() == "" || edtPassword.text.toString() == "") {
                Toast.makeText(applicationContext, "Silahkan masukkan email atau password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Email/password salah", Toast.LENGTH_SHORT).show()
            }

        }

        // 6 Mei 2023 merupakan kelas pengganti untuk tanggal 9 Mei 2023 (minggu 9)

        // email/password salah

        //login berhasil

//        btnLogin.setOnClickListener{
//            var edit = data.edit()
//            edit.putString("nama", "Marwah Nur Shafira")
//            edit.putString("email", "marwahnurshafira1705@gmail.com")
//            edit.putString("nim", "2107411008")
//            edit.putBoolean("isLogin", true)
//            edit.commit()
//        }
    }

    // untuk android studio yg terbaru, maka jdk-nya harus 17
}
