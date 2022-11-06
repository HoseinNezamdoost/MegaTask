package com.nzd.megatask.common

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
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

        fun getDescriptionTask(): String {
            val myVar = view.findViewById<TextInputEditText>(R.id.descriptionTask)
            return myVar.text.toString()
        }

        fun getDayTask(): String {
            val myVar = view.findViewById<AutoCompleteTextView>(R.id.chooseDay)
            return myVar.text.toString()
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

        fun addToPriority(onClick: (view:View) -> Unit) : TaskDialog{
            val myVar = view.findViewById<ImageView>(R.id.icon_add_priority)
            myVar.setOnClickListener {
                if (!isChecked){
                    myVar.setImageDrawable(ContextCompat.getDrawable(context , R.drawable.ic_star_select))
                    isChecked = true
                    Toast.makeText(context, "این کار به اولویت ها اضافه شد", Toast.LENGTH_SHORT).show()

                    onClick(it)
                }else{
                    myVar.setImageDrawable(ContextCompat.getDrawable(context , R.drawable.ic_star))
                    isChecked = false
                    Toast.makeText(context, "این کار از اولویت ها حذف شد", Toast.LENGTH_SHORT).show()

                    onClick(it)
                }
            }
            return this
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