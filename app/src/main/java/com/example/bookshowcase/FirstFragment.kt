package com.example.bookshowcase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bookshowcase.databinding.FragmentFirstBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val bookAPI = BookAPI()
    lateinit var gridView: GridView
    lateinit var bookGridAdapter: BookGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
        */


        val view = inflater.inflate(R.layout.fragment_first, container, false)
        gridView = view.findViewById(R.id.bookGridView)
        lifecycleScope.launch {
            fetchAndDisplayBooks()
        }
        return view



    }

    private suspend fun fetchAndDisplayBooks() {

        val books = bookAPI.fetchBooks()
        if (books != null) {
            bookGridAdapter = BookGridAdapter(requireContext(), books)
            gridView.adapter = bookGridAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // _binding = null
    }

}