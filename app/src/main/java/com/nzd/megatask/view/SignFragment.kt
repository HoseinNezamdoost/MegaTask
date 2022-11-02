package com.nzd.megatask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nzd.megatask.R
import com.nzd.megatask.adapter.WeekDaysAdapter
import com.nzd.megatask.common.weekDay
import kotlinx.android.synthetic.main.fragment_task.view.*

class SignFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }
}