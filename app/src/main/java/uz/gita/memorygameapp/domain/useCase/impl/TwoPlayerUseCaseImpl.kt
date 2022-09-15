package uz.gita.memorygameapp.domain.useCase.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import uz.gita.memorygameapp.data.models.CardEvent
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.domain.useCase.GameUseCase
import uz.gita.memorygameapp.domain.useCase.TwoPlayerUseCase
import javax.inject.Inject

class TwoPlayerUseCaseImpl @Inject constructor(

) : TwoPlayerUseCase {
    private var openCardId = -1
    private var openCardAmount = -1
    private var isActive = true
    private var countOpenedCard = 0
    private var level = LevelEnum.EASY
    private var parts = 1
    private var activeUser = 1
    private var user1Coin = 0
    private var user2Coin = 0


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
                if (openCardAmount == amount && openCardId == id) return@flow
                if (openCardId != id) {
                    emit(Pair(CardEvent.OPEN, id))
                    delay(1000)
                    if (openCardAmount == amount) {
                        emit(Pair(CardEvent.HIDE, id))
                        emit(Pair(CardEvent.HIDE, openCardId))
                        if (activeUser == 1) {
                            user1Coin += 1
                            emit(Pair(CardEvent.CORRECT1, user1Coin))
                        }else{
                            user2Coin += 1
                            emit(Pair(CardEvent.CORRECT2, user2Coin))
                        }
                        countOpenedCard += 2
                        if (level.x * level.y == countOpenedCard){
                            if (user1Coin == user2Coin){
                                emit(Pair(CardEvent.GAME_OVER,1))
                            }else if (user1Coin > user2Coin){
                                emit(Pair(CardEvent.GAME_OVER,0))
                            }else{
                                emit(Pair(CardEvent.GAME_OVER,2))
                            }
                            openCardAmount = 0
                        }
                    } else {
                        emit(Pair(CardEvent.CLOSE, id))
                        emit(Pair(CardEvent.CLOSE, openCardId))
                        activeUser = if (activeUser == 1) 2 else 1
                        emit(Pair(CardEvent.WRONG,activeUser))
                    }
                    openCardAmount = -1
                }
                isActive = true
            }
        }
    }

    override fun setLevel(levelTyPe: LevelEnum) {
        level = levelTyPe
    }

    override fun reloadGame() {
        parts --
    }

    override fun getAttempt(): Int {
        return 0
    }
}