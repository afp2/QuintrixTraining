package com.example.npsapiapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.npsapiapp.api.CampgroundResponse
import org.w3c.dom.Text

class NPSCampgroundsFragment : Fragment() {
    private lateinit var npsCampgroundsRecyclerView : RecyclerView
    private lateinit var campgroundsViewModel : CampgroundsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        campgroundsViewModel = ViewModelProvider(this).get(CampgroundsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nps_campgrounds, container, false)
        npsCampgroundsRecyclerView = view.findViewById(R.id.nps_campground_recycler_view)
        npsCampgroundsRecyclerView.layoutManager = GridLayoutManager(context, 1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campgroundsViewModel.campgrounds.observe(
            this.viewLifecycleOwner,
            Observer {
                Log.d(TAG, "Have campgrounds from view model $it")
                npsCampgroundsRecyclerView.adapter = CampgroundsAdapter(it)
            }
        )
    }

    private class CampgroundHolder(textView : TextView) : RecyclerView.ViewHolder(textView){
        val bindText : (CharSequence) -> Unit = textView::setText
    }

    private inner class CampgroundsAdapter(private val campgroundResponse: CampgroundResponse)
        : RecyclerView.Adapter<CampgroundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampgroundHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_campground, parent, false) as TextView
            return CampgroundHolder(view)
        }

        override fun onBindViewHolder(holder: CampgroundHolder, position: Int) {
            val campground = campgroundResponse.data[position]
            holder.bindText(campground.toString())
        }

        override fun getItemCount(): Int {
            return campgroundResponse.data.size
        }

    }

    companion object {
        fun newInstance() = NPSCampgroundsFragment()
    }

}