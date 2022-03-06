package com.yura.bwxplore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.yura.bwxplore.data.Location
import com.yura.bwxplore.data.LocationData.listData
import com.yura.bwxplore.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var rvPopular: RecyclerView
    private val list = ArrayList<Location>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        rvPopular = binding.rvPopular
//        rvPopular.setHasFixedSize(true)
//
//        list.addAll(listData)
//        rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        val popularAdapter = PopularAdapter(list)
//        rvPopular.adapter = popularAdapter
//
////        println(list)
//
//        val textView: TextView = binding.tvName
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val db = FirebaseFirestore.getInstance()
        list.addAll(listData)
        list.forEach{
            db.collection("places")
                .document(it.id.toString())
                .set(
                    Location(
                        it.id,
                        it.name,
                        it.lat,
                        it.long,
                        it.imageUrl,
                        it.popular
                    )
                )
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}