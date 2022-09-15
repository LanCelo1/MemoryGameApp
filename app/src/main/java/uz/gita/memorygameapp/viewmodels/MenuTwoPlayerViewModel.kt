package uz.gita.memorygameapp.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.memorygameapp.data.models.CardData

interface MenuTwoPlayerViewModel {
    fun getCardData()
    val getAllCardData : LiveData<List<CardData>>
}