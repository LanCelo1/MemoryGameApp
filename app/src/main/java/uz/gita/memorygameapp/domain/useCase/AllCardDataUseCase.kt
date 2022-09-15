package uz.gita.memorygameapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.utils.LevelEnum

interface AllCardDataUseCase {
    fun getAllData(levelEnum: LevelEnum): Flow<List<CardData>>
    fun getAllCards() : Flow<List<CardData>>
}