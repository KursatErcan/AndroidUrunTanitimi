package com.kursat.gittigeliyor.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kursat.gittigeliyor.MyAnimationUtils
import com.kursat.gittigeliyor.util.UserData
import com.kursat.gittigeliyor.databinding.ActivityLoginBinding
import com.kursat.gittigeliyor.client.ApiClient
import com.kursat.gittigeliyor.model.LoginData
import com.kursat.gittigeliyor.model.User
import com.kursat.gittigeliyor.model.UserLoginResult
import com.kursat.gittigeliyor.service.JsonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var bind : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

    }

    fun loginButtonClicked(view: View) {
        val animation = MyAnimationUtils(this)
        view.startAnimation(animation.scaleUp)
        view.startAnimation(animation.scaleDown)

        val email = bind.etEmail.text.toString()
        val pass = bind.etPassword.text.toString()
        if (!email.trim().isEmpty() && !pass.trim().isEmpty()){
            login(email, pass)
        }else{
            Toast.makeText(this,
                "Bilgileri eksiksiz girdiğinizden emin olun!",
                Toast.LENGTH_SHORT).show()
        }
    }

    fun signUpClicked(view: View) {
        val animation = MyAnimationUtils(this)
        view.startAnimation(animation.scaleUp)
        view.startAnimation(animation.scaleDown)
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun login(email: String, pass: String) {
        val service = ApiClient.getClient().create(JsonService::class.java)
        val dataService = service.userLogin(userEmail = email, userPass = pass)

        dataService.enqueue(object : Callback<LoginData>{
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                if (response.isSuccessful){
                    val u = response.body()
                    if (u != null){
                        checkStatus(u.userResult.get(0))
                    }
                }
            }

            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                Log.d("Failure User Login :", t.toString())
            }
        })
    }

    private fun checkStatus(userLoginResult: UserLoginResult){
        val status = userLoginResult.durum

        if(status){
            val user: User? = userLoginResult.user
            UserData.userId = user?.userId
            Toast.makeText(this, "Sizi buralarda görmek sevindirici Sn. ${user?.userName}", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ProductActivity::class.java)
            //intent.putExtra("user", user)
            //TODO : user ı main product activity e gönder
            startActivity(intent)
            finish()

            Log.d("Success User Login : ", user?.userName + user?.userEmail)
        }
        else{
            Toast.makeText(this, "Hatalı giriş yaptınız!", Toast.LENGTH_SHORT).show()
        }
    }

}