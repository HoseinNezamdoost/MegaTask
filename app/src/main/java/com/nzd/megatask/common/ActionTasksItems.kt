package com.nzd.megatask.common

import com.nzd.megatask.dataClass.Tasks

interface ActionTasksItems {
    fun addToPriory(tasks: Tasks)
    fun done(tasks: Tasks)
    fun doing(tasks: Tasks)
    fun edit(tasks: Tasks)
    fun delete(tasks: Tasks)
}