package com.nzd.megatask.view

import android.annotation.SuppressLint
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
import com.nzd.megatask.common.ActionWeekItems
import com.nzd.megatask.common.DialogManager
import com.nzd.megatask.common.weekDay
import com.nzd.megatask.dataClass.Tasks
import com.nzd.megatask.dataClass.WeekDays
import com.nzd.megatask.database.AppDataBase
import com.nzd.megatask.database.MegaSharedPreferences
import kotlinx.android.synthetic.main.fragment_task.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject

class TaskFragment : Fragment(), ActionTasksItems , ActionWeekItems{

    private val database: AppDataBase by inject()
    private val tasks = arrayListOf<Tasks>()
    private val tasksSorted = arrayListOf<Tasks>()
    private lateinit var adapterTask: TaskAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set list Tasks from database to list recycler view
        if (tasks.size == 0)
            tasks.addAll(database.getDao().getAll())
        else
            MegaSharedPreferences(requireContext()).setId(tasks.size)
        if (tasksSorted.size == 0){
            tasks.forEach {
                if (it.date == weekDay()[0].day){
                    tasksSorted.add(it)
                }
            }
        }
        //for week recycler view
        view.week_rc.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL, false
        )
        view.week_rc.adapter = WeekDaysAdapter(requireContext() ,weekDay() , this)

        //for task recycler view
        view.task_rc.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL, false
        )
        adapterTask = TaskAdapter(requireContext(), tasksSorted, this)
        view.task_rc.adapter = adapterTask
    }

    override fun addToPriory(task: Tasks) {

    }

    override fun done(task: Tasks) {
        task.isDown = true
        val i = database.getDao().update(task)
        adapterTask.adapterUpdate(task)

        Toast.makeText(requireContext(), "$i", Toast.LENGTH_SHORT).show()
    }

    override fun edit(task: Tasks) {
        val dialogTask = DialogManager.TaskDialog(requireContext())
        dialogTask.setTitleTask(task.title)
        dialogTask.setDescriptionTask(task.description)
        dialogTask.setDayTask(task.date)
        dialogTask.setOnClickListener {
            task.title = dialogTask.getTitleTask()
            task.description = dialogTask.getDescriptionTask()
            task.date = dialogTask.getDayTask()
            val i = database.getDao().update(task)
            adapterTask.adapterUpdate(task)

            Toast.makeText(requireContext(), "$i", Toast.LENGTH_SHORT).show()
        }
        dialogTask.build()
    }

    override fun delete(task: Tasks) {
        val i = database.getDao().delete(task)
        Toast.makeText(requireContext(), "$i", Toast.LENGTH_SHORT).show()
        if (i == 1)
            adapterTask.delete(task)
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
    fun onTask(task: Tasks) {
        adapterTask.insert(task)
        val i = database.getDao().insert(task)
        tasks.add(task)
        Toast.makeText(requireContext(), "$i", Toast.LENGTH_SHORT).show()
        Log.i("TAG", "onTask: $task , $i")
    }

    override fun onClickWeeks(weekDays: WeekDays) {
        tasksSorted.clear()
        tasks.forEach {
            if (it.date == weekDays.day){
                tasksSorted.add(it)
            }
        }
        adapterTask.notifyDataSetChanged()
    }

}