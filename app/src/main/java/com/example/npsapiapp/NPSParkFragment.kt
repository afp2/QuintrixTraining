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
import com.example.npsapiapp.api.ParksResponse

class NPSParkFragment : Fragment() {
    private lateinit var npsRecyclerView : RecyclerView
    private lateinit var parkViewModel : ParkViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        val npsLiveData : LiveData<ParksResponse> = NPSFetcher().getParks()
        npsLiveData.observe(
            this,
            Observer { responseString ->
                Log.d(TAG, "Parks received: $responseString")
            }
        )

         */
        parkViewModel = ViewModelProvider(this).get(ParkViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nps, container, false)
        npsRecyclerView = view.findViewById(R.id.nps_recycler_view)
        npsRecyclerView.layoutManager = GridLayoutManager(context, 1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parkViewModel.parks.observe(
            this.viewLifecycleOwner,
            Observer {
                Log.d(TAG, "Have parks from view model ${it.data}")
                npsRecyclerView.adapter = ParkAdapter(it)
            }
        )
    }

    private class ParkHolder(textView: TextView) : RecyclerView.ViewHolder(textView) {
        val bindText : (CharSequence) -> Unit = textView::setText
    }

    private inner class ParkAdapter(private val parksResponse : ParksResponse)
        : RecyclerView.Adapter<ParkHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkHolder {
            //val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_park, parent, false) as TextView
            return ParkHolder(view)
        }

        override fun onBindViewHolder(holder: ParkHolder, position: Int) {
            val park = parksResponse.data[position]
            //sort of works. maybe want to override park to string method and it'll look better
            holder.bindText(park.toString())

        }

        override fun getItemCount(): Int {
            return parksResponse.data.size
        }


    }

    companion object {
        fun newInstance() = NPSParkFragment()
    }
}