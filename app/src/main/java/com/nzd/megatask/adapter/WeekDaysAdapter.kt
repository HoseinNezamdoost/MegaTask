package com.nzd.megatask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nzd.megatask.R
import com.nzd.megatask.common.implementSpringAnimationTrait
import com.nzd.megatask.dataClass.WeekDays
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rc_week.view.*

class WeekDaysAdapter(private val weeks:List<WeekDays>) : RecyclerView.Adapter<WeekDaysAdapter.WeekDaysViewHolder>() {

    inner class WeekDaysViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {

            }
            containerView.implementSpringAnimationTrait()
        }

        fun bind(weekDays: WeekDays) {
            containerView.days_tv.text = weekDays.day
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDaysViewHolder {
        return WeekDaysViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rc_week, parent , false))
    }

    override fun onBindViewHolder(holder: WeekDaysViewHolder, position: Int) {
        holder.bind(weeks[position])
    }

    override fun getItemCount(): Int {
       return weeks.size
    }
}

