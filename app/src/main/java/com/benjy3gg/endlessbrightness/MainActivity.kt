package com.benjy3gg.endlessbrightness

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import kotlin.math.roundToInt

class MainActivity : Activity() {

    var brightness: Int = 255;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Endless Brightness"
        actionOnService(Actions.STOP, 100)

        val canWriteSettings = Settings.System.canWrite(this);
        if (!canWriteSettings) {
            val intent = Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + this.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        findViewById<Button>(R.id.btnStartService).let {
            it.setOnClickListener {
                log("START THE FOREGROUND SERVICE ON DEMAND")
                actionOnService(Actions.START, 0)
            }
        }

        findViewById<Button>(R.id.btnStopService).let {
            it.setOnClickListener {
                log("STOP THE FOREGROUND SERVICE ON DEMAND")
                actionOnService(Actions.STOP, 100)
            }
        }

        /*findViewById<Button>(R.id.btnSettings).let {
            it.setOnClickListener {
                startActivityForResult(Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        }*/

        findViewById<Button>(R.id.btnClose).let {
            it.setOnClickListener {
                actionOnService(Actions.START, brightness)
                this.finish();
            }
        }

        findViewById<SeekBar>(R.id.seekBar).let {
            it.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                val temp: Double = seekBar.progress / 4.0
                brightness = (temp*255).roundToInt()
                setBrightnesss(brightness)
            }
        }) }

    }

    private fun setBrightnesss(_brightness: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(getApplicationContext())) {

                val cResolver = this.getApplicationContext().getContentResolver();
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, _brightness);
            }
        }
    }

    private fun actionOnService(action: Actions, brightness: Int) {
        if (getServiceState(this) == ServiceState.STOPPED && action == Actions.STOP) return
        Intent(this, EndlessService::class.java).also {
            it.action = action.name
            it.putExtra("brightness", brightness)
            Log.d("EXTRAS", it.extras.toString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                log("Starting the service in >=26 Mode")
                startForegroundService(it)
                return
            }
        }
    }
}
