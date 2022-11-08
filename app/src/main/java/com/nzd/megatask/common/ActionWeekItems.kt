package com.nzd.megatask.common

import com.nzd.megatask.dataClass.WeekDays

interface ActionWeekItems {
    fun onClickWeeks(weekDays: WeekDays)
}