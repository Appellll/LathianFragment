package com.example.test1_c14240086.lathianfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class fKali : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_kali, container, false)
        val listView = view.findViewById<ListView>(R.id.listHistory)
        val adapter = HistoryAdapter(requireContext(), MainActivity.historyKali)
        listView.adapter = adapter
        return view
    }
}
