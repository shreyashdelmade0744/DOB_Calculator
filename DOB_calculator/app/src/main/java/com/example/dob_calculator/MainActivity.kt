package com.example.dob_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var selecteddate : TextView
    private lateinit var minute_box : TextView
    private lateinit var hours_box : TextView
    private lateinit var days_box : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selecteddate = findViewById(R.id.selecteddate)
        minute_box = findViewById(R.id.minute_box)
        hours_box = findViewById(R.id.hours_box)
        days_box = findViewById(R.id.days_box)
        val btndatepicker : Button = findViewById(R.id.buttonDatePicker)
        btndatepicker.setOnClickListener {

            clickdatepicker()

        }

    }

     private fun clickdatepicker() {
         val mycalendar = Calendar.getInstance()
         val year = mycalendar.get(Calendar.YEAR)
         val month = mycalendar.get(Calendar.MONTH)
         val day = mycalendar.get(Calendar.DAY_OF_MONTH)
         val dpd = DatePickerDialog(this,
         { _,selectedyear, selectedmonth, selecteddayOfMonth ->
             Toast.makeText(this, " day is $selecteddayOfMonth,month is ${selectedmonth+1},year is $selectedyear", Toast.LENGTH_SHORT).show()
             val ddate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
             selecteddate.text = ddate

             val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
             val thedate = sdf.parse(ddate)
             thedate.let {
                 val selecteddateinminute =
                     thedate.time / 60000  //thedate.time gives millisecond since 1 jan 1970 till selected date

                 val currentdate = sdf.parse(sdf.format(System.currentTimeMillis())) //give millisec from 1 jan 1970 till now
                 currentdate.let {
                 val currentdateinminute = currentdate.time / 60000

                 val diffinminutes = currentdateinminute - selecteddateinminute
                     val diffinhours = diffinminutes/60
                     val diffindays = diffinhours/24
                     minute_box.text = diffinminutes.toString()
                     hours_box.text = diffinhours.toString()
                     days_box.text = diffindays.toString()
             }}
         },year,month,day
         )
         dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
         dpd.show()

    }
}