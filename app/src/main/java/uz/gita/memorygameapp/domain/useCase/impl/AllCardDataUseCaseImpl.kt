package uz.gita.memorygameapp.domain.useCase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.data.repository.AppRepository
import uz.gita.memorygameapp.domain.useCase.AllCardDataUseCase
import javax.inject.Inject

class AllCardDataUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : AllCardDataUseCase {
    override fun getAllData(levelEnum: LevelEnum) : Flow<List<CardData>> = flow {
        val datas = repository.getCardDatas(levelEnum)
        val list = ArrayList<CardData>()
        list.addAll(datas)
        list.addAll(datas)
        list.shuffle()
        emit(list)
    }.flowOn(Dispatchers.IO)

    override fun getAllCards(): Flow<List<CardData>> = flow{
        emit(repository.getAllCards())
    }.flowOn(Dispatchers.IO)
}