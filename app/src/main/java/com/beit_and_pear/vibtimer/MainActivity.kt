package com.beit_and_pear.vibtimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import com.beit_and_pear.vibtimer.MainActivity.Constants.Companion.START_TIMER
import java.util.*

class MainActivity : AppCompatActivity() {

    class Constants {
        companion object {
            const val START_TIMER = 1000
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ログ
        Log.i("MyActivity", "onCreate")

        val textTimer = findViewById<TextView>(R.id.textTimer)
        val imgBtnStart = findViewById<ImageButton>(R.id.imgBtnStart)
        val imgBtnReset = findViewById<ImageButton>(R.id.imgBtnReset)
        val timerRunning = false

        // スタートボタンを押したら
        imgBtnStart.setOnClickListener {
            println(timerRunning)
            if (timerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        // リセットボタンを押したら
        imgBtnReset.setOnClickListener {
            resetTimer()
        }
    }

    // タイマー実行
    private fun startTimer() {


    }
    // 一時停止
    private fun pauseTimer() {

    }
    // リセット
    private fun resetTimer() {

    }
    // 時刻の表示
    private fun updateCountDownText() {
        var textView: TextView? = null
        var minutes = (START_TIMER / 1000) / 60
        var seconds = (START_TIMER / 1000) % 60
        var timerFormatted = String.format(Locale.getDefault().toString(), "%02d:%02d", minutes, seconds)
        textView?.text = timerFormatted
    }
}