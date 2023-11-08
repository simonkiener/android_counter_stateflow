package ch.bfh.cas.mad.counter

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrease: Button
    private lateinit var buttonAlert: Button

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewCounter = findViewById(R.id.textView_counter)
        buttonIncrease = findViewById(R.id.button_increase)
        buttonAlert = findViewById(R.id.button_alert)
        refreshCounter()
    }

    override fun onResume() {
        super.onResume()
        buttonIncrease.setOnClickListener {
            counter += 1
            refreshCounter()
        }
        buttonAlert.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        buttonIncrease.setOnClickListener(null)
        buttonAlert.setOnClickListener(null)
    }

    private fun refreshCounter() {
        textViewCounter.text = counter.toString()
    }
}