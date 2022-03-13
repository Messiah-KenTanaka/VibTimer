package com.beit_and_pear.vibtimer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import android.widget.Spinner
import com.beit_and_pear.vibtimer.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var vibrator: Vibrator

    lateinit var mAdView : AdView

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
            vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(longArrayOf(0, 600, 400, 200, 400, 200, 400, 600), 2)
            binding.imgBtn.setImageResource(R.drawable.btn_stop)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 広告
        MobileAds.initialize(this) {}

        val adView = AdView(this)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        adView.adSize = AdSize.SMART_BANNER
//        サンプル  ca-app-pub-3940256099942544/6300978111
//        本物  ca-app-pub-3324255919208405/7761710419
        adView.adUnitId = "ca-app-pub-3324255919208405/7761710419"

        //タイトルバー非表示
        supportActionBar?.hide()

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // ログ
        Log.i("MyActivity", "onCreate")

        binding.textTimer.text = "5:00"
        var timer = MyCountDownTimer(5 * 60 * 1000, 100)

        binding.imgBtn.setOnClickListener {
            timer.isRunning = when (timer.isRunning) {
                true -> {
                    timer.cancel()
                    // バイブレーターを停止
                    vibrator.cancel()
                    binding.imgBtn.setImageResource(R.drawable.btn_start)
                    false
                }
                false -> {
                    timer.start()
                    // バイブレーターを停止
                    vibrator.cancel()
                    binding.imgBtn.setImageResource(R.drawable.btn_reset)
                    true
                }
            }
        }

        // スピナーが押された時の処理
        binding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    timer.cancel()
                    binding.imgBtn.setImageResource(
                        R.drawable.btn_start
                    )
                    val spinner = parent as? Spinner
                    val item = spinner?.selectedItem as? String
                    item?.let {
                        if (it.isNotEmpty()) {
                            binding.textTimer.text = it
                        }
                        val times = it.split(":")
                        val min = times[0].toLong()
                        val sec = times[1].toLong()
                        timer = MyCountDownTimer((min * 60 + sec) * 1000, 100)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        // シークバーが押された時の処理
        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    timer.cancel()
                    binding.imgBtn.setImageResource(R.drawable.btn_start)
                    val min = progress / 600L
                    val sec = progress / 10L
                    binding.textTimer.text = "%1d:%2$02d".format(0, sec)
                    timer = MyCountDownTimer(progress * 100L, 100)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            }
        )
    }


}