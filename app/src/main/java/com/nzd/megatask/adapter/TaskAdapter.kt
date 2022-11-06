package com.nzd.megatask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nzd.megatask.R
import com.nzd.megatask.common.ActionTasksItems
import com.nzd.megatask.dataClass.Tasks
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_dialog_task.view.*
import kotlinx.android.synthetic.main.item_rc_task.view.*

class TaskAdapter(val context: Context , val tasks:List<Tasks> , val actionTasksItems: ActionTasksItems) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        var isChecked = true
            fun bind(tasks: Tasks){
                containerView.prof_tv.text = tasks.title.substring(0,2)
                containerView.title_tv.text = tasks.title
                containerView.description_tv.text = tasks.description

                containerView.icon_add_priority.setOnClickListener {
                    if (!isChecked){
                        containerView.icon_add_priority.setImageDrawable(ContextCompat.getDrawable(context , R.drawable.ic_star))
                        isChecked = true
                        actionTasksItems.addToPriory()
                    }else{
                        containerView.icon_add_priority.setImageDrawable(ContextCompat.getDrawable(context , R.drawable.ic_star_select))
                        isChecked = false
                        actionTasksItems.addToPriory()
                    }
                }
                containerView.icon_menu.setOnClickListener {
                    showPopupMenu(it)
                }

            }

        private fun showPopupMenu(view: View){
            val popup = PopupMenu(context , view)
            popup.inflate(R.menu.task_item_menu)
            popup.setOnMenuItemClickListener {

                when(it.itemId){
                    R.id.down -> actionTasksItems.done()
                    R.id.downing -> actionTasksItems.doing()
                    R.id.edit -> actionTasksItems.edit()
                    R.id.delete -> actionTasksItems.delete()
                }
                return@setOnMenuItemClickListener true
            }
            popup.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rc_task , parent , false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}