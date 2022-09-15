package uz.gita.memorygameapp.domain.useCase.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import uz.gita.memorygameapp.data.models.CardEvent
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.domain.useCase.GameUseCase
import javax.inject.Inject

class GameUseCaseImpl @Inject constructor(

) : GameUseCase {
    private val list = listOf(
        listOf(16,15,14,14,13,12,11,10,9,8),
        listOf(24,23,22,21,20,19,18,16,15,14),
        listOf(44,40,38,36,34,32,32,30,28,28),
    )
    private var openCardId = -1
    private var openCardAmount = -1
    private var isActive = true
    private var countOpenedCard = 0
    private var level = LevelEnum.EASY
    private var parts = 1
    private var attempt = list[if (level == LevelEnum.EASY) 0 else if (level == LevelEnum.MEDIUM) 1 else 2][parts-1]
    private val COUNT_ALL_PARTS = 11

    init {

    }

    override fun clickCard(id : Int, amount : Int) : Flow<Pair<CardEvent,Int>> = flow{
        Timber.d("work bang")
        if (isActive) {
            isActive = false
            if (openCardAmount == -1) {
                openCardAmount = amount
                openCardId = id
                emit(Pair(CardEvent.OPEN, id))
                isActive = true
            } else {
//                if (openCardAmount == amount && openCardId == id) return@flow
                if (openCardId != id) {
                    emit(Pair(CardEvent.OPEN, id))
                    delay(1000)
                    if (openCardAmount == amount) {
                        emit(Pair(CardEvent.HIDE, id))
                        emit(Pair(CardEvent.HIDE, openCardId))
                        emit(Pair(CardEvent.CORRECT, -1))

                        countOpenedCard += 2
                        if (level.x * level.y == countOpenedCard){
                            openCardAmount = 0
                            parts++
                            attempt = list[if (level == LevelEnum.EASY) 0 else if (level == LevelEnum.MEDIUM) 1 else 2][parts-1]
                            if (parts == COUNT_ALL_PARTS){
                                emit(Pair(CardEvent.END_PART,-1))
                            }else emit(Pair(CardEvent.NEXT,parts))
                            isActive = true
                            openCardAmount = -1
                            countOpenedCard = 0
                            return@flow
                        }
                    } else {
                        emit(Pair(CardEvent.CLOSE, id))
                        emit(Pair(CardEvent.CLOSE, openCardId))
                        emit(Pair(CardEvent.WRONG,-1))
                    }
                    if (attempt-1 == 0 ) {
                        emit(Pair(CardEvent.GAME_OVER, parts))
                        parts = 1
                        openCardAmount = 0
                        attempt = list[if (level == LevelEnum.EASY) 0 else if (level == LevelEnum.MEDIUM) 1 else 2][parts-1]
                    }
                    if (attempt-1 > 0){
                        attempt--
                        emit(Pair(CardEvent.ATTEMPT,attempt))
                    }

                    openCardAmount = -1
                }
                isActive = true
            }
        }
    }

    override fun setLevel(levelTyPe: LevelEnum) {
        level = levelTyPe
        attempt = list[if (level == LevelEnum.EASY) 0 else if (level == LevelEnum.MEDIUM) 1 else 2][parts-1]
    }

    override fun reloadGame() {
        parts --
    }

    override fun getAttempt(): Int {
        return list[if (level == LevelEnum.EASY) 0 else if (level == LevelEnum.MEDIUM) 1 else 2][parts-1]
    }
}