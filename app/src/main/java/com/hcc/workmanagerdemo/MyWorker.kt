package com.hcc.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

/**
 * Created by hecuncun on 2021/8/10
 */
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val name =  inputData.getString(INPUT_DATA_KEY)
        Log.d("MyWorker", "doWork:${name}start ")
        Thread.sleep(3000)
        Log.d("MyWorker", "doWork:${name}end ")
        return Result.success(workDataOf(OUTPUT_DATA_KEY to "$name output"))
    }
}