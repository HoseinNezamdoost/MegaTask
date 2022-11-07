package com.nzd.megatask.common

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nzd.megatask.R

class DialogManager {

    class TaskDialog(val context: Context) {
        private val view =
            LayoutInflater.from(context).inflate(R.layout.item_dialog_task, null, false)
        private var isChecked = false

        private val dialog = Dialog(context)

        fun getTitleTask(): String {
            val myVar = view.findViewById<TextInputEditText>(R.id.titleTask)
            return myVar.text.toString()
        }

        fun setTitleTask(title:String){
            view.findViewById<TextInputEditText>(R.id.titleTask).setText(title)
        }

        fun getDescriptionTask(): String {
            val myVar = view.findViewById<TextInputEditText>(R.id.descriptionTask)
            return myVar.text.toString()
        }

        fun setDescriptionTask(description:String){
            view.findViewById<TextInputEditText>(R.id.descriptionTask).setText(description)
        }

        fun getDayTask(): String {
            val myVar = view.findViewById<AutoCompleteTextView>(R.id.chooseDay)
            return myVar.text.toString()
        }

        fun setDayTask(day:String){
            view.findViewById<AutoCompleteTextView>(R.id.chooseDay).setText(day)
        }

        fun setOnClickListener(onClick: (view: View) -> Unit) :TaskDialog{
            view.findViewById<MaterialButton>(R.id.addBtn).setOnClickListener {
                onClick(it)
                dialog.dismiss()
            }
            return this
        }

        fun getPriority() : Boolean{
            return isChecked
        }

        fun build() {
            view.findViewById<MaterialButton>(R.id.cancelBtn).setOnClickListener {
                dialog.dismiss()
            }
            val week_days = context.resources.getStringArray(R.array.week_days)
            val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, week_days)
            view.findViewById<AutoCompleteTextView>(R.id.chooseDay).setAdapter(arrayAdapter)
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            dialog.show()
        }

    }

}