package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.service.autofill.SavedDatasetsInfo
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY

class MainActivity : AppCompatActivity() {

    lateinit var start : Button
    lateinit var restart: Button
    lateinit var timer: Chronometer
    var isRunning = false
    var stopTime : Long = 0
    var timeStopped : Long = 0
    //make a classwide static constant in Kotlin
    companion object {
        // all your static constants go here
        val TAG = "MainActivity"
        val STATE_TIME = "display time"
        val STATE_RUN = "IsRunning"
    }

    //use this to preserve state through orientation changes
    override fun onSaveInstanceState(outState: Bundle) {
        //calculate the displayTime if it is running
            //display time = SystemClock.elaspedRealTime - timeStopped
            //convert timestopped to displayTime
            timeStopped -= (SystemClock.elapsedRealtime())
        // Save the key-value pairs to tbe bundle before the superclass call
        outState?.run {
            outState.putLong(STATE_TIME, timeStopped)
            outState.putBoolean(STATE_RUN, isRunning)
        }
        super.onSaveInstanceState(outState)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")

        wireWidgets()
        //restore saved instance states if it exists
        if (savedInstanceState != null)
        {
            timeStopped = savedInstanceState.getLong(STATE_TIME)
            timer.base = timeStopped
            isRunning = savedInstanceState.getBoolean(STATE_RUN)
            if(isRunning){
                timerRun()
            }
            if(!isRunning) {
                timer.stop()
                timer.base = timeStopped
                isRunning = false
                start.text = "Start"
                timerStop()
            }
        }

        start.setOnClickListener {
            if(!isRunning) // when you run
            {
                timerRun()
            }
            else //when you stop
            {
                timerStop()
            }
        }

        restart.setOnClickListener {
            timer.stop()
            timer.base = SystemClock.elapsedRealtime()
            timeStopped = 0
            stopTime = 0
            isRunning = false
            start.text = "Start"
        }

    }

    private fun timerStop() {
        stopTime = SystemClock.elapsedRealtime() //time you stopped
        isRunning = false
        start.text = "Start"
        timer.stop()
    }

    private fun timerRun() {
        //display = current time - base
        //display when stopped = stopTime - base
        timeStopped += (SystemClock.elapsedRealtime()-stopTime) //time stopped is the base
        timer.base = timeStopped
        isRunning = true
        start.text = "Stop"
        timer.start()
    }

    private fun wireWidgets() {
        start = findViewById(R.id.button_main_start)
        restart = findViewById(R.id.button_main_restart)
        timer = findViewById(R.id.chronometer_main_stopwatch)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

}
