package com.example.hse_android_lab_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hse_android_lab_3.newsAdapter.NewsAdapter
import com.example.hse_android_lab_3.retrofit.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val newsAdapter = NewsAdapter(ArrayList())
        recyclerView.adapter = newsAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsdata.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val newsApi = retrofit.create(NewsApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val results = newsApi.getNews().results

            withContext(Dispatchers.Main) {
                for (news in results) {
                    newsAdapter.addNews(news)
                }
            }
        }
    }
}