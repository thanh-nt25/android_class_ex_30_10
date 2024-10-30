package com.example.ex2_search_on_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtSearch: EditText
    private lateinit var listViewStudents: ListView
    private lateinit var studentAdapter: ArrayAdapter<String>
    private val students = mutableListOf(
        Student("Nguyễn Văn A", "20211234"),
        Student("Trần Thị B", "20219876"),
        Student("Phạm Văn C", "20213456"),
        Student("Lê Thị D", "20215678"),
        Student("Hoàng Văn E", "20217654")
    )
    private val studentNames = students.map { "${it.name} - ${it.mssv}" }.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edtSearch = findViewById(R.id.edtSearch)
        listViewStudents = findViewById(R.id.listViewStudents)


        studentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
        listViewStudents.adapter = studentAdapter


        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterStudents(s.toString())
            }
        })
    }

    private fun filterStudents(query: String) {
        val filteredList = if (query.length > 2) {
            students.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
            }.map { "${it.name} - ${it.mssv}" }
        } else {
            studentNames
        }
        studentAdapter.clear()
        studentAdapter.addAll(filteredList)
        studentAdapter.notifyDataSetChanged()
    }
}