package com.example.ex3_create_inf_form

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ex3_create_inf_form.AddressHelper
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var edtMSSV: EditText
    private lateinit var edtFullName: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var btnToggleCalendar: Button
    private lateinit var calendarView: CalendarView
    private lateinit var spinnerProvince: Spinner
    private lateinit var spinnerDistrict: Spinner
    private lateinit var spinnerWard: Spinner
//    private lateinit var checkSports: CheckBox
//    private lateinit var checkMovies: CheckBox
//    private lateinit var checkMusic: CheckBox
    private lateinit var edtHobby: EditText
    private lateinit var checkAgree: CheckBox
    private lateinit var btnSubmit: Button

    private lateinit var addressHelper: AddressHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edtMSSV = findViewById(R.id.edtMSSV)
        edtFullName = findViewById(R.id.edtFullName)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        btnToggleCalendar = findViewById(R.id.btnToggleCalendar)
        calendarView = findViewById(R.id.calendarView)
        spinnerProvince = findViewById(R.id.spinnerProvince)
        spinnerDistrict = findViewById(R.id.spinnerDistrict)
        spinnerWard = findViewById(R.id.spinnerWard)
        edtHobby = findViewById(R.id.edtHobby)
//        checkSports = findViewById(R.id.checkSports)
//        checkMovies = findViewById(R.id.checkMovies)
//        checkMusic = findViewById(R.id.checkMusic)
        checkAgree = findViewById(R.id.checkAgree)
        btnSubmit = findViewById(R.id.btnSubmit)


        addressHelper = AddressHelper(resources)
        populateProvinceSpinner()

        btnToggleCalendar.setOnClickListener {
            calendarView.visibility = if (calendarView.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        btnSubmit.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Form Submit thanh cong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateProvinceSpinner() {
        val provinces = addressHelper.getProvinces()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        spinnerProvince.adapter = adapter

        spinnerProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                populateDistrictSpinner(selectedProvince)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun populateDistrictSpinner(province: String) {
        val districts = addressHelper.getDistricts(province)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, districts)
        spinnerDistrict.adapter = adapter

        spinnerDistrict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedDistrict = districts[position]
                populateWardSpinner(province, selectedDistrict)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun populateWardSpinner(province: String, district: String) {
        val wards = addressHelper.getWards(province, district)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, wards)
        spinnerWard.adapter = adapter
    }

    private fun validateForm(): Boolean {
        if (edtMSSV.text.isEmpty() || edtFullName.text.isEmpty() || edtEmail.text.isEmpty() ||
            edtPhone.text.isEmpty() || radioGroupGender.checkedRadioButtonId == -1 ||
            !checkAgree.isChecked) {
            Toast.makeText(this, "Nhap thong tin vao cac truong sau day:", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
