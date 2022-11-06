package com.nzd.megatask.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nzd.megatask.R
import com.nzd.megatask.adapter.WeekDaysAdapter
import com.nzd.megatask.common.ActionTasksItems
import com.nzd.megatask.common.KEY
import com.nzd.megatask.common.weekDay
import com.nzd.megatask.dataClass.Tasks
import com.nzd.megatask.database.AppDataBase
import kotlinx.android.synthetic.main.fragment_task.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject

class TaskFragment : Fragment() , ActionTasksItems {

    val database : AppDataBase by inject()
    val tasks = arrayListOf<Tasks>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //for week recycler view
        val view = inflater.inflate(R.layout.fragment_task, container, false)
            view.week_rc.layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL, false)
        view.week_rc.adapter = WeekDaysAdapter(weekDay())

        //for get task from dialog add task

        return view
    }

    override fun addToPriory() {
        Toast.makeText(requireContext(), "addToPriory", Toast.LENGTH_SHORT).show()
    }

    override fun done() {
        Toast.makeText(requireContext(), "done", Toast.LENGTH_SHORT).show()
    }

    override fun doing() {
        Toast.makeText(requireContext(), "doing", Toast.LENGTH_SHORT).show()
    }

    override fun edit() {
        Toast.makeText(requireContext(), "edit", Toast.LENGTH_SHORT).show()
    }

    override fun delete() {
        Toast.makeText(requireContext(), "delete", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onTask(task: Tasks){
        tasks.add(task)
        Log.i("TAG", "onTask: $tasks")
    }

}