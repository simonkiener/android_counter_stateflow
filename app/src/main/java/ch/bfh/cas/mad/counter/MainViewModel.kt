package ch.bfh.cas.mad.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private var _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun increaseCounter() {
        _counter.value += 1
    }
}