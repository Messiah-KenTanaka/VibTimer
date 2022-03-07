package com.beit_and_pear.vibtimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.beit_and_pear.vibtimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long)
        : CountDownTimer(millisInFuture, countDownInterval) {

        var isRunning = false

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.textTimer.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.textTimer.text = "00:00"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ログ
        Log.i("MyActivity", "onCreate")

        binding.textTimer.text = "5:00"
        var timer = MyCountDownTimer(5 * 60 * 1000, 100)

        binding.imgBtnStart.setOnClickListener {
            timer.isRunning = when (timer.isRunning) {
                true -> {
                    timer.cancel()
                    binding.textTimer.text = "5:00"
                    binding.imgBtnStart.setImageResource(
                        R.drawable.btn_start)
                    false
                }
                false -> {
                    timer.start()
                    binding.imgBtnStart.setImageResource(
                        R.drawable.btn_reset)
                    true
                }
            }
        }
    }
}