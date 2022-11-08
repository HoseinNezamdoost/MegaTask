package com.nzd.megatask.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.nzd.megatask.R
import com.nzd.megatask.common.implementSpringAnimationTrait
import com.nzd.megatask.dataClass.WeekDays
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rc_week.view.*

class WeekDaysAdapter(val context: Context, private val weeks: List<WeekDays>) :
    RecyclerView.Adapter<WeekDaysViewHolder>() {

    var rowItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDaysViewHolder {
        return WeekDaysViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rc_week, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeekDaysViewHolder, position: Int) {
        holder.layout.implementSpringAnimationTrait()
        holder.layout.setOnClickListener {
            rowItem = position
            notifyDataSetChanged()
        }
        if (rowItem == position) {
            holder.title.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.purple
                )
            )
        }
        else{
            holder.title.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
        holder.title.text = weeks[position].day
    }

    override fun getItemCount(): Int {
        return weeks.size
    }
}
class WeekDaysViewHolder(containerView: View) :
    RecyclerView.ViewHolder(containerView){

    val title:TextView = containerView.findViewById<TextView>(R.id.days_tv)
    val layout:MaterialCardView = containerView.findViewById(R.id.cardView)

}
