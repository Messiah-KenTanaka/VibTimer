package com.beit_and_pear.vibtimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

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

        // スタートボタンを押したら
        imgBtnStart.setOnClickListener {

        }

        // リセットボタンを押したら
        imgBtnReset.setOnClickListener {

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
    private fun update() {

    }
}