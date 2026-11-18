package com.example.test1_c14240086.lathianfragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

data class HistoryItem(val text: String, val result: Double)

class MainActivity : AppCompatActivity() {

    companion object {
        val historyTambah = ArrayList<HistoryItem>()
        val historyKurang = ArrayList<HistoryItem>()
        val historyKali = ArrayList<HistoryItem>()
        val historyBagi = ArrayList<HistoryItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _angka1 = findViewById<EditText>(R.id.angka1)
        val _angka2 = findViewById<EditText>(R.id.angka2)

        val _btnTambah = findViewById<Button>(R.id.btnTambah)
        val _btnKurang = findViewById<Button>(R.id.btnKurang)
        val _btnKali = findViewById<Button>(R.id.btnKali)
        val _btnBagi = findViewById<Button>(R.id.btnBagi)

        _btnTambah.setOnClickListener {
            val s1 = _angka1.text.toString()
            val s2 = _angka2.text.toString()
            if (s1.isNotEmpty() && s2.isNotEmpty()) {
                val n1 = s1.toDoubleOrNull() ?: 0.0
                val n2 = s2.toDoubleOrNull() ?: 0.0
                val res = n1 + n2
                val text = "$n1 + $n2 = $res"
                historyTambah.add(HistoryItem(text, res))
                replaceFragment(fTambah())
            } else {
                Toast.makeText(this, "Mohon isi kedua angka", Toast.LENGTH_SHORT).show()
            }
        }

        _btnKurang.setOnClickListener {
            val s1 = _angka1.text.toString()
            val s2 = _angka2.text.toString()
            if (s1.isNotEmpty() && s2.isNotEmpty()) {
                val n1 = s1.toDoubleOrNull() ?: 0.0
                val n2 = s2.toDoubleOrNull() ?: 0.0
                val res = n1 - n2
                val text = "$n1 - $n2 = $res"
                historyKurang.add(HistoryItem(text, res))
                replaceFragment(fKurang())
            } else {
                Toast.makeText(this, "Mohon isi kedua angka", Toast.LENGTH_SHORT).show()
            }
        }

        _btnKali.setOnClickListener {
            val s1 = _angka1.text.toString()
            val s2 = _angka2.text.toString()
            if (s1.isNotEmpty() && s2.isNotEmpty()) {
                val n1 = s1.toDoubleOrNull() ?: 0.0
                val n2 = s2.toDoubleOrNull() ?: 0.0
                val res = n1 * n2
                val text = "$n1 * $n2 = $res"
                historyKali.add(HistoryItem(text, res))
                replaceFragment(fKali())
            } else {
                Toast.makeText(this, "Mohon isi kedua angka", Toast.LENGTH_SHORT).show()
            }
        }

        _btnBagi.setOnClickListener {
            val s1 = _angka1.text.toString()
            val s2 = _angka2.text.toString()
            if (s1.isNotEmpty() && s2.isNotEmpty()) {
                val n1 = s1.toDoubleOrNull() ?: 0.0
                val n2 = s2.toDoubleOrNull() ?: 0.0
                if (n2 == 0.0) {
                    Toast.makeText(this, "Tidak dapat membagi dengan nol", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val res = n1 / n2
                val text = "$n1 / $n2 = $res"
                historyBagi.add(HistoryItem(text, res))
                replaceFragment(fBagi())
            } else {
                Toast.makeText(this, "Mohon isi kedua angka", Toast.LENGTH_SHORT).show()
            }
        }

        if (savedInstanceState == null) {
            replaceFragment(fTambah())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}

class HistoryAdapter(context: Context, items: List<HistoryItem>) :
    ArrayAdapter<HistoryItem>(context, android.R.layout.simple_list_item_1, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        val item = getItem(position)
        view.text = item?.text
        if (item != null && item.result < 0) {
            view.setBackgroundColor(Color.RED)
            view.setTextColor(Color.WHITE)
        } else {
            view.setBackgroundColor(Color.TRANSPARENT)
            view.setTextColor(Color.BLACK)
        }
        return view
    }
}
