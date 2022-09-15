package uz.gita.memorygameapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.memorygameapp.data.models.CardEvent
import uz.gita.memorygameapp.utils.LevelEnum

interface GameUseCase {

    fun clickCard(id: Int, amount: Int): Flow<Pair<CardEvent, Int>>
    fun setLevel(count: LevelEnum)
    fun reloadGame()
    fun getAttempt() : Int
}