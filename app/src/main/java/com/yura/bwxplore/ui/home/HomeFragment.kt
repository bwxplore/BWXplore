package com.yura.bwxplore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.yura.bwxplore.data.firebase.entities.Location
import com.yura.bwxplore.data.remote.entities.ArticlesItem
import com.yura.bwxplore.databinding.FragmentHomeBinding
import com.yura.bwxplore.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        setProfile()
        setPopularPlaces()
        getNews()
    }

    private fun getNews() {
        viewModel.getNews().observe(viewLifecycleOwner, {
            val listItems = ArrayList<ArticlesItem>()
            listItems.addAll(it)
            val newsAdapter = NewsAdapter(listItems)
            with(_binding?.rvNews) {
                this?.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                this?.adapter = newsAdapter
                this?.setHasFixedSize(true)
            }
        })
    }

    private fun setPopularPlaces() {
        viewModel.getPopularPlaces().observe(viewLifecycleOwner, {
            val listItems = ArrayList<Location>()
            listItems.addAll(it)
            val popularAdapter = PopularAdapter(listItems)
            with(_binding?.rvPopular) {
                this?.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                this?.adapter = popularAdapter
                this?.setHasFixedSize(true)
            }
        })
    }

    private fun setProfile() {
        val user = auth.currentUser
        _binding?.tvName?.text = user?.displayName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}