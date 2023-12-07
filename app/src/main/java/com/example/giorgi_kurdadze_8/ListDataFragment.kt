package com.example.giorgi_kurdadze_8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListDataFragment : Fragment() {
    private lateinit var databaseHelper: DatabaseHelper;
    private lateinit var recyclerView: RecyclerView;
    private lateinit var adapter: BooksAdapter;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_data, container, false)

        databaseHelper = DatabaseHelper(requireContext());
        recyclerView = view.findViewById(R.id.recyclerView);


        adapter = BooksAdapter(emptyList());
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(requireContext());

        displayData();

        return view;
    }

    private fun displayData() {
        val data = databaseHelper.getAllBooks();
        adapter.updateData(data);
    }


    private class BooksAdapter(private var books: List<BooksModel>) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

        class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val bookName: TextView = itemView.findViewById(R.id.textView2);
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
            return BookViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val book = books[position]

            holder.bookName.text = "Book Name: ${book.name}";
        }

        override fun getItemCount(): Int {
            return books.size;
        }


        fun updateData(newData: List<BooksModel>) {
            books = newData;
            notifyDataSetChanged();
        }

    }
}

