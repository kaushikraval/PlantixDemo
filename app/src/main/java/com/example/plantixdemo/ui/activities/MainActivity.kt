package com.example.plantixdemo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.plantixdemo.R
import com.example.plantixdemo.adapters.PlantixDataAdapter
import com.example.plantixdemo.databinding.ActivityMainBinding
import com.example.plantixdemo.models.PlantixResponseModel
import com.example.plantixdemo.models.Row
import com.example.plantixdemo.network.ServiceApi
import com.example.plantixdemo.utils.ProgressDialog
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Default)

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)

        getDataFromApi()
    }

    private fun getDataFromApi() {
        progressDialog.dialog.show()
        coroutineScope.launch {
            try {

                val response = ServiceApi.retrofitService.getData()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {

                        Log.d("getChatList", response.code().toString())
                        Log.d("getChatList", response.body().toString())

                        val list: List<Row> = response.body()!!.rows!!
                        binding.ListRV.adapter = PlantixDataAdapter(this@MainActivity, list)

                        progressDialog.dialog.dismiss()
                    }
                }else{
                    withContext(Dispatchers.Main){
                        Log.d(TAG, "something wrong")
                        progressDialog.dialog.dismiss()
                    }
                }

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                progressDialog.dialog.dismiss()
            }
        }
    }
}