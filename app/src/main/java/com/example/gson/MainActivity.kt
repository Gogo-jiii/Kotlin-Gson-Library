package com.example.gson

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gson.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private var json: String? = null
    private var gson: Gson? = null
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        gson = Gson()

        binding.btnModelToJson.setOnClickListener {
            val userModel = UserModel("IT wala")
            json = gson!!.toJson(userModel)
            binding.txtResult.text = json
        }

        binding.btnJsonToModel.setOnClickListener {
            if (!TextUtils.isEmpty(json)) {
                try {
                    val model = gson!!.fromJson(json, UserModel::class.java)
                    binding.txtResult.text = model.toString()
                } catch (exception: Exception) {
                    Toast.makeText(
                        this@MainActivity, "Click model to json first.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@MainActivity, "Click model to json first.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnArraylistToAndFromList.setOnClickListener {
            val result: String
            val listType: Type = object : TypeToken<List<UserModel?>?>() {}.type
            val list: ArrayList<UserModel> = ArrayList()
            list.add(UserModel("A"))
            list.add(UserModel("B"))
            list.add(UserModel("C"))
            json = gson!!.toJson(list, listType)
            val list1: ArrayList<UserModel> = gson!!.fromJson(json, listType)
            result = """
                 Json: $json
                 List: $list1
                 """.trimIndent()
            binding.txtResult.text = result
        }

    }
}