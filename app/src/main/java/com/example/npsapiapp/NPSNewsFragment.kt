package com.example.npsapiapp

import android.content.ContentValues.TAG
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.npsapiapp.api.NPSFetcher
import com.example.npsapiapp.api.NewsResponse

class NPSNewsFragment : Fragment() {
    private lateinit var npsNewsRecyclerView : RecyclerView
    private lateinit var newsViewModel : NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nps_news, container, false)
        npsNewsRecyclerView = view.findViewById(R.id.nps_news_recycler_view)
        npsNewsRecyclerView.layoutManager = GridLayoutManager(context, 1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.news.observe(
            this.viewLifecycleOwner,
            Observer {
                Log.d(TAG, "Have news from view model ${it.data}")
                npsNewsRecyclerView.adapter = NewsAdapter(it)
            }
        )
    }

    private class NewsHolder(textView : TextView) : RecyclerView.ViewHolder(textView){
        val bindText : (CharSequence) -> Unit = textView::setText
    }

    private inner class NewsAdapter(private val newsResponse : NewsResponse)
        : RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false) as TextView
            return NewsHolder(view)
        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            val news = newsResponse.data[position]
            holder.bindText(news.toString())
        }

        override fun getItemCount(): Int {
            return newsResponse.data.size
        }
    }

    companion object {
        fun newInstance() = NPSNewsFragment()
    }
}