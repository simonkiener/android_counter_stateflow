package ch.bfh.cas.mad.counter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrease: Button
    private lateinit var buttonAlert: Button

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelProvider = ViewModelProvider(this)
        mainViewModel = viewModelProvider.get(MainViewModel::class.java)
        textViewCounter = findViewById(R.id.textView_counter)
        buttonIncrease = findViewById(R.id.button_increase)
        buttonAlert = findViewById(R.id.button_alert)

        lifecycleScope.launch {
            mainViewModel.counter.collectLatest {
                textViewCounter.text = it.toString()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        buttonIncrease.setOnClickListener {
            mainViewModel.increaseCounter()
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
}