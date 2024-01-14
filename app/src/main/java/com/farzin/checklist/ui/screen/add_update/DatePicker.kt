package com.farzin.checklist.ui.screen.add_update

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.font.Typeface
import com.example.picker.utils.PersianCalendarUtils
import com.farzin.checklist.R
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener



fun datePicker(context:Context,onDatePickerSucceed:(String)->Unit) {




    val picker = PersianDatePickerDialog(context)
        .setPositiveButtonString("باشه")
        .setNegativeButton("بیخیال")
        .setTodayButton("امروز")
        .setTodayButtonVisible(true)
        .setMinYear(PersianDatePickerDialog.THIS_YEAR)
        .setMaxYear(1500)
//        .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
//        .setMaxDay(PersianDatePickerDialog.THIS_DAY)
//        .setInitDate(1370, 3, 13)
//        .setActionTextColor(Color.GRAY)
//        .setTypeFace()
        .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
        .setShowInBottomSheet(true)
        .setListener(object : PersianPickerListener {
            override fun onDateSelected(persianPickerDate: PersianPickerDate) {
                Log.e("TAG", "onDateSelected: " + persianPickerDate.timestamp) //675930448000
                Log.e(
                    "TAG",
                    "onDateSelected: " + persianPickerDate.gregorianDate
                ) //Mon Jun 03 10:57:28 GMT+04:30 1991
                Log.e(
                    "TAG",
                    "onDateSelected: " + persianPickerDate.persianLongDate
                ) // دوشنبه  13  خرداد  1370
                Log.e("TAG", "onDateSelected: " + persianPickerDate.persianMonthName) //خرداد
                Log.e(
                    "TAG",
                    "onDateSelected: " + PersianCalendarUtils.isPersianLeapYear(persianPickerDate.persianYear)
                ) //true
                Toast.makeText(
                    context,
                    "${persianPickerDate.persianYear}/${persianPickerDate.persianMonth}/${persianPickerDate.persianDay}",
                    Toast.LENGTH_SHORT
                ).show()
                onDatePickerSucceed("${persianPickerDate.persianYear} / ${persianPickerDate.persianMonth} / ${persianPickerDate.persianDay}")
            }

            override fun onDismissed() {

            }
        })
    picker.show()


}