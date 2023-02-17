package uz.itschool.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_create_account.*

class LoginActivity : AppCompatActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var userList = mutableListOf<User>()
        var type = object : TypeToken<List<User>>(){}.type
        var gson = Gson()
        var getPreferences = getSharedPreferences("login", MODE_PRIVATE)
        var str = getPreferences.getString("Users", "")
        if (str ==""){

        }else{
            userList = gson.fromJson(str, type)
        }
        login.setOnClickListener {
            if (em.text.isEmpty()){
                Toast.makeText(applicationContext, "Enter email", Toast.LENGTH_LONG).show()
            }else{
                if (pas.text.isEmpty()){
                    Toast.makeText(applicationContext, "Enter password", Toast.LENGTH_LONG).show()
                }else{
                    var a = 0
                    for (i in 0..userList.size-1){
                        if (userList[i].email==em.text.toString() && userList[i].password==pas.text.toString()){
                            a=1
                        }
                    }
                    if (a==1){
                        val intent = Intent(this, MenuActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext, "Incorrect e-mail or password", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        donthave.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

    }

}