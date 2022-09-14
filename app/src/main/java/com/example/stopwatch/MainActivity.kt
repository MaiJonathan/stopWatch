package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var start : Button
    lateinit var restart: Button
    lateinit var timer: Chronometer
    var running = false
    var stopTime : Long = 0
    var startTime: Long = 0
    var timeStopped : Long = 0
    //make a classwide static constant in Kotlin
    companion object {
        // all your static constants go here
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")

        wireWidgets()

        start.setOnClickListener {
            if(!running) // when you run
            {
                startTime = SystemClock.elapsedRealtime() //time you start again
                timeStopped += (startTime-stopTime) //time stopped is total time elasped - (the difference in times from stop to start)
                timer.base = timeStopped
                running = true
                start.text = "Stop"
                timer.start()
            }
            else //when you stop
            {
                stopTime = SystemClock.elapsedRealtime() //time you stopped
                running = false
                start.text = "Start"
                timer.stop()
            }
        }

        restart.setOnClickListener {
            if(running) {
                timeStopped = SystemClock.elapsedRealtime()
                startTime = SystemClock.elapsedRealtime()
            }
            if(!running)
            {
                stopTime = SystemClock.elapsedRealtime();
            }
        }

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
