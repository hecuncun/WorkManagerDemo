package com.hcc.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by hecuncun on 2021/8/10
 */
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("MyWorker", "doWork:start ")
        Thread.sleep(3000)
        Log.d("MyWorker", "doWork:end ")
        return Result.success()
    }
}