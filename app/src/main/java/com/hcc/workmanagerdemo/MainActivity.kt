package com.hcc.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hcc.workmanagerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val workerManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            bindView(this)
        }
    }

    private fun bindView(activityMainBinding: ActivityMainBinding) {
        with(activityMainBinding){
            button.setOnClickListener {
                val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
                    .build()
                workerManager.enqueue(workRequest)
            }
        }
    }
}