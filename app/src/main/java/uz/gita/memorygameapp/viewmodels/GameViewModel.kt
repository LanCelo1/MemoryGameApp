package uz.gita.memorygameapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.utils.LevelEnum

interface GameViewModel {
    fun getAllCardData(levelEnum : LevelEnum)
    fun clickCard(id: Int, amount: Int)
    fun setLevel(count: LevelEnum)
    fun getAttempt()
    fun reloadGame()

    val hideCardLiveData: LiveData<Int>
    val getAllCardDataLiveData: LiveData<List<CardData>>
    val closeCardLiveData: LiveData<Int>
    val openCardLiveData: LiveData<Int>
    val nextPartLiveData: MutableLiveData<Int>
    val changeAttemptLiveData: LiveData<Int>
    val endPartLiveData: LiveData<Unit>
    val gameOverLiveData: LiveData<Int>
    val correctCaseLiveData: LiveData<Unit>
    val wrongCaseLiveData: LiveData<Unit>
}