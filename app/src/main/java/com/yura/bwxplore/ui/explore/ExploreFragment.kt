package com.yura.bwxplore.ui.explore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yura.bwxplore.data.firebase.entities.Location
import com.yura.bwxplore.databinding.FragmentExploreBinding
import com.yura.bwxplore.ui.detail.MapsActivity
import com.yura.bwxplore.ui.detail.MapsActivity.Companion.ID
import com.yura.bwxplore.ui.detail.MapsActivity.Companion.VALUE
import com.yura.bwxplore.viewmodel.ViewModelFactory

class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : ExploreViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[ExploreViewModel::class.java]

        getPlaces()
    }

    private fun getPlaces() {
        viewModel.getAllPlaces().observe(viewLifecycleOwner) {
            val listItems = ArrayList<Location>()
            listItems.addAll(it)
            val placesAdapter = PlacesAdapter(listItems)
            with(_binding?.rvPlaces) {
                this?.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                this?.adapter = placesAdapter
                this?.setHasFixedSize(true)
            }

            binding.btnSubmit.setOnClickListener {
                if (placesAdapter.selected.size > 0) {
                    val stringBuilder = StringBuilder()
                    val idBuilder = StringBuilder()

                    for (i in 0 until placesAdapter.selected.size) {
                        stringBuilder.append("+")
                        stringBuilder.append(placesAdapter.selected[i].lat)
                        stringBuilder.append(" ")
                        stringBuilder.append(placesAdapter.selected[i].long)
                        idBuilder.append("${placesAdapter.selected[i].id},")
                    }
                    if (placesAdapter.selected.size > 2){
                        stringBuilder.delete(0,1)
                        idBuilder.dropLast(1)
                        startActivity(Intent(context, MapsActivity::class.java)
                            .putExtra(VALUE, stringBuilder.toString())
                            .putExtra(ID, idBuilder.toString()))
                    } else {
                        Toast.makeText(context, "Mohon pilih 3 atau lebih lokasi", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "No Selection", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}