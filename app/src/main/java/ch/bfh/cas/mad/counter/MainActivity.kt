package ch.bfh.cas.mad.counter

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrease: Button
    private lateinit var buttonAlert: Button

    private lateinit var mainViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelProvider = ViewModelProvider(this)
        mainViewModel = viewModelProvider.get(MyViewModel::class.java)
        textViewCounter = findViewById(R.id.textView_counter)
        buttonIncrease = findViewById(R.id.button_increase)
        buttonAlert = findViewById(R.id.button_alert)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.counter.collectLatest{
                    textViewCounter.text = it.toString()
                }
            }
        }
      //  refreshCounter()
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