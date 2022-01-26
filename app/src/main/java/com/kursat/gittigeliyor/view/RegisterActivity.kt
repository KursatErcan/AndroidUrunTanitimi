package com.kursat.gittigeliyor.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kursat.gittigeliyor.MyAnimationUtils
import com.kursat.gittigeliyor.client.ApiClient
import com.kursat.gittigeliyor.databinding.ActivityRegisterBinding
import com.kursat.gittigeliyor.model.RegisterData
import com.kursat.gittigeliyor.service.JsonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var bind : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)

    }

    fun registerButtonClicked(view: View) {
        val animation = MyAnimationUtils(this)
        view.startAnimation(animation.scaleUp)
        view.startAnimation(animation.scaleDown)


        val name = bind.etName.text.toString()
        val surname = bind.etSurname.text.toString()
        val phone = bind.etPhone.text.toString()
        val email = bind.etEmail.text.toString()
        val pass = bind.etPassword.text.toString()
        if (!name.trim().isEmpty() && !surname.trim().isEmpty() && !phone.trim().isEmpty() && !email.trim().isEmpty() && !pass.trim().isEmpty()){
            bind.progressBar.visibility = View.VISIBLE
            bind.btnRegister.isClickable = false
            register(name,surname,phone,email,pass)
        }else{
            Toast.makeText(this,
                "Bilgileri eksiksiz girdiğinizden emin olun!",
                Toast.LENGTH_SHORT).show()
        }
    }

    fun signInClicked(view: View) {
        val animation = MyAnimationUtils(this)
        view.startAnimation(animation.scaleUp)
        view.startAnimation(animation.scaleDown)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun register(name: String, surname: String, phone: String, email: String, pass: String) {
        val service = ApiClient.getClient().create(JsonService::class.java)
        val dataService = service.userRegister(userName = name, userSurname = surname, userPhone = phone, userMail = email, userPass = pass)

        dataService.enqueue(object : Callback<RegisterData> {
            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                if(response.isSuccessful){
                    val r = response.body()
                    if (r != null) {
                        checkStatus(r.user.get(0))
                    }else{
                        Log.d("Failure User Register :", "null geliyo")

                    }
                }
                else{
                    Log.d("Failure User Register :", "is not success")

                }
            }

            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                Log.d("Failure User Register :", t.toString())
            }

        })
    }

    private fun checkStatus(userRegister: RegisterData.User?) {
        val status = userRegister?.durum
        Log.d("Register Activity", "in check status")
        bind.progressBar.visibility = View.INVISIBLE
        bind.btnRegister.isClickable = true
        Toast.makeText(this, userRegister?.mesaj, Toast.LENGTH_SHORT).show()

        if (status == true) {

            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }

    }


}