package com.hcc.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import com.hcc.workmanagerdemo.databinding.ActivityMainBinding
const val INPUT_DATA_KEY = "input_data_key"
const val OUTPUT_DATA_KEY = "output_data_key"
const val WORK_A_NAME = "A"
const val WORK_B_NAME = "B"
class MainActivity : AppCompatActivity() {
    //初始化workerManager
    private val workerManager =WorkManager.getInstance(this)

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
                //为worker 添加执行约束
                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
                //创建workRequest  加入到workerManager队列
                val workRequestA = OneTimeWorkRequestBuilder<MyWorker>()
                    .setConstraints(constraints)//设置约束
                    .setInputData(workDataOf(INPUT_DATA_KEY to WORK_A_NAME))//传入一个数据  键值对形式
                    .build()
                val workRequestB = OneTimeWorkRequestBuilder<MyWorker>()
                    .setConstraints(constraints)//设置约束
                    .setInputData(workDataOf(INPUT_DATA_KEY to WORK_B_NAME))//传入一个数据  键值对形式
                    .build()
                //执行链式任务
                workerManager.beginWith(workRequestA).then(workRequestB).enqueue()
                //监听执行结果输出
                workerManager.getWorkInfoByIdLiveData(workRequestA.id).observe(this@MainActivity, Observer {
                    Log.d("work","onCreate ${it.state}")
                    if (it.state == WorkInfo.State.SUCCEEDED){
                        Log.d("work","onCreate ${it.outputData.getString(OUTPUT_DATA_KEY)}")
                    }

                })
            }
        }
    }
}