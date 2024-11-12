package com.example.pertemuan11

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan11.databinding.ActivityMainBinding
import com.example.pertemuan11.model.Data
import com.example.pertemuan11.model.Users
import com.example.pertemuan11.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  // Declare binding here
    private lateinit var listView: ListView
    private var profileList: List<Data> = listOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listView = binding.listView  // Assuming listView is defined in your layout file

        // Fetching data from API
        fetchUserData()
    }

    private fun fetchUserData() {
        ApiClient.getInstance().getAllUsers().enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    profileList = response.body()?.data ?: listOf()
                    val adapter = ProfileAdapter(this@MainActivity, profileList)
                    listView.adapter = adapter

                    // Set item click listener for each user
                    listView.setOnItemClickListener { _, _, position, _ ->
                        val selectedUser = profileList[position]
                        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                            putExtra("user_id", selectedUser.id)
                            putExtra("user_name", "${selectedUser.firstName} ${selectedUser.lastName}")
                            putExtra("user_email", selectedUser.email)
                            putExtra("user_avatar", selectedUser.avatar)
                        }
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
