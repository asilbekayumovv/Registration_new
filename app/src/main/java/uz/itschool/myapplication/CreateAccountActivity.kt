package uz.itschool.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        haveaccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        var userList = mutableListOf<User>()
        var type = object : TypeToken<List<User>>(){}.type
        var gson = Gson()

        var getPreferences = getSharedPreferences("login", MODE_PRIVATE)
        var edit = getPreferences.edit()

        signup.setOnClickListener {
            if (edittextinputName.text.isEmpty() || email.text.isEmpty() || textinputMobile.text.isEmpty() || password.text.isEmpty()){
                Toast.makeText(applicationContext, "Fill all strokes", Toast.LENGTH_LONG).show()
            }else{
                var user_name = edittextinputName.text.toString()
                var user_email = email.text.toString()
                var user_phone = textinputMobile.text.toString()
                var user_password = password.text.toString()
                var str = getPreferences.getString("users", "")
                if (str == ""){
                    userList = mutableListOf<User>()
                }else{
                    userList = gson.fromJson(str, type)
                }
                userList.add(User(user_email, user_name, user_password, user_phone))
                var s = gson.toJson(userList)
                edit.putString("Users", s).commit()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}