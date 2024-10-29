package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DieViewModel : ViewModel() {
    private val dieRoll = MutableLiveData<Int>()
    var hasSeenSelection = false
    fun getDieRoll() : LiveData<Int> {
        return dieRoll
    }

    fun rollDie(dieSides: Int = 6) {
        hasSeenSelection = false
        dieRoll.value = Random.nextInt(dieSides) + 1
    }
}