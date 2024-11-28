package com.example.bookshowcase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.bookshowcase.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    val bookAPI = BookAPI()
    lateinit var bookGridAdapter: BookGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                lifecycleScope.launch { fetchAndDisplayBooks() }
                Toast.makeText(requireContext(), "Libros cargados correctamente", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            fetchAndDisplayBooks()
        }

        binding.bookGridView.setOnItemClickListener { adapter, _, position, _ ->
            val book = adapter.getItemAtPosition(position) as Book
            val args = Bundle().apply {
                putSerializable("item", book)
            }
            NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment, args)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun fetchAndDisplayBooks() {

        val books = bookAPI.fetchBooks()
        if (books != null) {
            bookGridAdapter = BookGridAdapter(requireContext(), books)
            binding.bookGridView.adapter = bookGridAdapter
        }

    }

}