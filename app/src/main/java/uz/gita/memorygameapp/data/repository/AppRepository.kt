package uz.gita.memorygameapp.data.repository

import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.utils.LevelEnum

interface AppRepository {
    fun getCardDatas(level : LevelEnum): List<CardData>
    fun getAllCards() : List<CardData>
}