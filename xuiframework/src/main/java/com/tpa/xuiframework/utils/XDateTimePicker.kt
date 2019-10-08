package com.tpa.xuiframework.utils

import android.support.v7.app.AppCompatActivity
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog

class XDateTimePicker(
    appCompatActivity: AppCompatActivity,
    calendar: XPersianCalendar = XPersianCalendar()
) : XDatePicker(appCompatActivity, calendar) {

    protected var hour: Int = 0
    protected var min: Int = 0
    var timeListener: ((TimePickerDialog, Int, Int, Int) -> Unit)? = null

    fun showDateTimePicker(
        listener: ((datePickerDialog: TimePickerDialog, day: Int, month: Int, year: Int) -> Unit)? = null
    ): XDatePicker {
        timeListener = listener
        buildAndShowDatePicker()
        return this
    }

    fun showDateTimePicker(
        day: Int,
        month: Int,
        year: Int,
        hour: Int,
        min: Int,
        listener: ((datePickerDialog: TimePickerDialog, day: Int, month: Int, year: Int) -> Unit)? = null
    ): XDatePicker {
        timeListener = listener

        this.year = year
        this.month = month
        this.day = day
        this.hour = hour
        this.min = min

        buildAndShowDatePicker()
        return this
    }

    private fun buildAndShowDatePicker() {
        val datePickerDialog =
            DatePickerDialog.newInstance({ datePickerDialog: DatePickerDialog, day: Int, month: Int, year: Int ->
                showTimePicker()
            }, this.year, this.month, this.day)

        datePickerDialog.show(getActivity().fragmentManager, "TAG")
    }

    var timePicker: TimePickerDialog? = null

    private fun showTimePicker(){
        timePicker = TimePickerDialog.newInstance(
            { radialPickerLayout: RadialPickerLayout, i: Int, i1: Int ->
                if (timeListener != null){
                    timeListener!!(timePicker!!, day, month, year)
                }
            },
            hour,
            min,
            true
        )

        timePicker!!.show(getActivity().fragmentManager, "Datepickerdialog")
    }

}