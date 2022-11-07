package com.nzd.megatask.common

import com.nzd.megatask.dataClass.Tasks

interface ActionTasksItems {
    fun addToPriory(task: Tasks)
    fun done(task: Tasks)
    fun edit(task: Tasks)
    fun delete(task: Tasks)
}