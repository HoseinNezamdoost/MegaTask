package com.nzd.megatask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nzd.megatask.R
import com.nzd.megatask.common.ActionTasksItems
import com.nzd.megatask.common.implementSpringAnimationTrait
import com.nzd.megatask.dataClass.Tasks
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rc_task.view.*

class TaskAdapter(
    val context: Context,
    private val tasks: ArrayList<Tasks>,
    val actionTasksItems: ActionTasksItems
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(task: Tasks) {
            containerView.prof_tv.text = task.title.substring(0, 2)
            containerView.title_tv.text = task.title
            containerView.description_tv.text = task.description

            if (task.isDown) {
                containerView.background_task.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.green
                    )
                )
                containerView.prof_tv.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_circle_green
                    )
                )
            }else{
                containerView.background_task.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                containerView.prof_tv.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_circle_red
                    )
                )
            }


            containerView.addPriory.setOnClickListener {
                if (!task.isPriory) {
                    containerView.addPriory.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_star_select
                        )
                    )
                    task.isPriory = true
                    actionTasksItems.addToPriory(task)
                } else {
                    containerView.addPriory.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_star
                        )
                    )
                    task.isPriory = false
                    actionTasksItems.addToPriory(task)
                }
            }
            containerView.icon_menu.setOnClickListener {
                showPopupMenu(it, task)
            }

            containerView.implementSpringAnimationTrait()
            containerView.setOnClickListener {  }

        }

        private fun showPopupMenu(view: View, task: Tasks) {
            val popup = PopupMenu(context, view)
            popup.inflate(R.menu.task_item_menu)
            popup.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.down -> actionTasksItems.done(task)
                    R.id.downing -> actionTasksItems.doing(task)
                    R.id.edit -> actionTasksItems.edit(task)
                    R.id.delete -> actionTasksItems.delete(task)
                }
                return@setOnMenuItemClickListener true
            }
            popup.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_rc_task, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun insert(task: Tasks) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

}