package com.nzd.megatask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nzd.megatask.R
import com.nzd.megatask.common.DialogManager
import com.nzd.megatask.dataClass.Tasks
import com.nzd.megatask.database.MegaSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = MegaSharedPreferences(this)
        var id = sharedPreferences.getId()
        //bottomNavigation
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(1).isEnabled = false

        //setBottomNavigation with fragments
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        //mini fab buttons
        fab.setOnClickListener {
            if (isChecked) {
                //fab.rotation += 45f
                fab.animate().setDuration(400).rotation(0f)

                fab_sign.visibility = View.GONE
                fab_task.visibility = View.GONE
                isChecked = false

            } else {
                //fab.rotation -= 45f
                fab.animate().setDuration(400).rotation(45f)

                fab_sign.visibility = View.VISIBLE
                fab_task.visibility = View.VISIBLE
                isChecked = true
            }

        }

        fab_task.setOnClickListener {
            // dialog task
            val taskDialogManager = DialogManager.TaskDialog(this)
            taskDialogManager
                .setOnClickListener {
                    id += 1
                    sharedPreferences.setId(id)
                    val task = Tasks(
                        id,
                        taskDialogManager.getTitleTask(),
                        taskDialogManager.getDescriptionTask(),
                        taskDialogManager.getDayTask(),
                        taskDialogManager.getPriority()
                    )
                    EventBus.getDefault().post(task)
                }
                .build()

            fab.animate().setDuration(400).rotation(0f)

            fab_sign.visibility = View.GONE
            fab_task.visibility = View.GONE
            isChecked = false
        }

        fab_sign.setOnClickListener {
            Toast.makeText(this, "fab sign", Toast.LENGTH_SHORT).show()
            fab.animate().setDuration(400).rotation(0f)

            fab_sign.visibility = View.GONE
            fab_task.visibility = View.GONE
            isChecked = false
        }

    }
}