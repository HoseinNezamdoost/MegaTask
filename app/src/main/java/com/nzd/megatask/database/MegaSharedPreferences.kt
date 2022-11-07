package com.nzd.megatask.database

import android.content.Context
import android.content.SharedPreferences
import com.nzd.megatask.common.MEGA_SHARED

class MegaSharedPreferences(context: Context){

    var editor:SharedPreferences.Editor
    private var sharedPreferences : SharedPreferences
    init {
        sharedPreferences = context.getSharedPreferences(MEGA_SHARED , Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun setId(id:Int){
        editor.putInt("id" , id)
        editor.commit()
    }

    fun getId():Int{
        return sharedPreferences.getInt("id" , 0)
    }

}