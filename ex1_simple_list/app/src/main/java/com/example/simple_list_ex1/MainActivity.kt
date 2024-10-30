package com.example.simple_list_ex1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.class_exercise_30_10.R

class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var txtError: TextView
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bai1)

        edtNumber = findViewById(R.id.edtNumber)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        btnShow = findViewById(R.id.btnShow)
        txtError = findViewById(R.id.txtError)
        listView = findViewById(R.id.listView)

        btnShow.setOnClickListener {
            val nStr = edtNumber.text.toString()
            txtError.visibility = TextView.GONE

            if (nStr.isEmpty() || nStr.toIntOrNull() == null || nStr.toInt() <= 0) {
                txtError.text = "Please enter a valid positive integer"
                txtError.visibility = TextView.VISIBLE
            } else {
                val n = nStr.toInt()
                val resultList = when {
                    radioEven.isChecked -> generateEvenNumbers(n)
                    radioOdd.isChecked -> generateOddNumbers(n)
                    radioSquare.isChecked -> generatePerfectSquares(n)
                    else -> emptyList()
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
                listView.adapter = adapter
            }
        }
    }

    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun generateOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun generatePerfectSquares(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            result.add(i * i)
            i++
        }
        return result
    }
}
