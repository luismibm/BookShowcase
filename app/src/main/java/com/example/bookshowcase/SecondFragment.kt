package com.example.bookshowcase

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bookshowcase.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: Bundle? = arguments

        if (args != null) {
            val item: Book? = args.getSerializable("item") as Book?
            if (item != null) {
                updateUi(item)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(book: Book) {

        val context: Context = context as Context
        Glide.with(context).load(book.photo).into(binding.bookImageDetails)
        binding.bookNameDetails.text = book.name
        binding.bookAuthorDetails.text = book.author
        binding.bookPagesDetails.text = "${book.pages} páginas"
        binding.bookPublisherDetails.text = "Publicado por ${book.publisher}"
        binding.bookDescriptionDetails.text = book.description

    }

}