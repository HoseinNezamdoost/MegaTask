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
import com.nzd.megatask.adapter.TaskAdapter
import com.nzd.megatask.adapter.WeekDaysAdapter
import com.nzd.megatask.common.ActionTasksItems
import com.nzd.megatask.common.KEY
import com.nzd.megatask.common.weekDay
import com.nzd.megatask.dataClass.Tasks
import com.nzd.megatask.database.AppDataBase
import kotlinx.android.synthetic.main.fragment_task.*
import kotlinx.android.synthetic.main.fragment_task.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject

class TaskFragment : Fragment() , ActionTasksItems {

    private val database : AppDataBase by inject()
    private val tasks = arrayListOf<Tasks>()
    private lateinit var adapterTask : TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //set list Tasks from database to list recycler view
        tasks.addAll(database.getDao().getAll())

        //for week recycler view
        val view = inflater.inflate(R.layout.fragment_task, container, false)
            view.week_rc.layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL, false)
        view.week_rc.adapter = WeekDaysAdapter(weekDay())

        //for task recycler view
        view.task_rc.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL, false)
        adapterTask = TaskAdapter(requireContext() , tasks , this)
        view.task_rc.adapter = adapterTask

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
        adapterTask.insert(task)
        val i = database.getDao().insert(task)
        Log.i("TAG", "onTask: $i")
    }

}