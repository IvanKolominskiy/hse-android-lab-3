package com.example.hse_android_lab_3.newsAdapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hse_android_lab_3.R
import com.example.hse_android_lab_3.retrofit.News

class NewsAdapter(dataset: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var dataset: ArrayList<News> = ArrayList()

    init {
        this.dataset = dataset
    }

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getTextView().text = dataset[position].title + "\n\n" + dataset[position].description

        holder.getButton().setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, android.net.Uri.parse(dataset[position].link))
            holder.itemView.context.startActivity(browserIntent)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addNews(news: News) {
        dataset.add(news)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val textView: TextView
        private val button: Button

        init {
            textView = v.findViewById(R.id.text_view)
            button = v.findViewById(R.id.button)
        }

        fun getTextView(): TextView {
            return textView
        }

        fun getButton(): Button {
            return button
        }
    }
}