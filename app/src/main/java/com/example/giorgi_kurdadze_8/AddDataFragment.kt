package com.example.giorgi_kurdadze_8

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class AddDataFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enter, container, false)

        databaseHelper = DatabaseHelper(requireContext());

        val editText: EditText = view.findViewById(R.id.editTextText);
        val button: Button = view.findViewById(R.id.button);

        button.setOnClickListener {
            val name = editText.text.toString();
            saveDataToDatabase(name);

            editText.text.clear();
        }

        return view
    }


    private fun saveDataToDatabase(name: String) {
        databaseHelper.saveBook(name);
    }


}