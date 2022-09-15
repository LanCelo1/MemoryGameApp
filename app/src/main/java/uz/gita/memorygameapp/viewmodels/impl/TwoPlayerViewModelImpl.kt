package uz.gita.memorygameapp.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.data.models.CardEvent
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.domain.useCase.AllCardDataUseCase
import uz.gita.memorygameapp.domain.useCase.GameUseCase
import uz.gita.memorygameapp.domain.useCase.TwoPlayerUseCase
import uz.gita.memorygameapp.viewmodels.GameViewModel
import uz.gita.memorygameapp.viewmodels.TwoPlayerViewModel
import javax.inject.Inject

@HiltViewModel
class TwoPlayerViewModelImpl @Inject constructor(
    private val useCase: AllCardDataUseCase,
    private val gameUseCase : TwoPlayerUseCase
) : ViewModel(),TwoPlayerViewModel {
    override var getAllCardDataLiveData = MutableLiveData<List<CardData>>()
    override var openCardLiveData = MutableLiveData<Int>()
    override var closeCardLiveData = MutableLiveData<Int>()
    override var hideCardLiveData = MutableLiveData<Int>()
    override var nextPartLiveData = MutableLiveData<Int>()
    override var endPartLiveData = MutableLiveData<Unit>()
    override var changeAttemptLiveData = MutableLiveData<Int>()
    override var gameOverLiveData = MutableLiveData<Int>()
    override var correctCase1LiveData = MutableLiveData<Int>()
    override var correctCase2LiveData = MutableLiveData<Int>()
    override var wrongCaseLiveData = MutableLiveData<Int>()


    override fun getAllCardData(levelEnum: LevelEnum) {
        Timber.d("work postvalue know")
        useCase.getAllData(levelEnum).onEach {
            Timber.d("work postvalue $it")
            getAllCardDataLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }
    override fun clickCard(id : Int,amount : Int){
        gameUseCase.clickCard(id,amount).onEach {
            Timber.d("work s${it.first}")
            when(it.first){
                CardEvent.OPEN->{
                    openCardLiveData.value = it.second!!
                }
                CardEvent.CLOSE->{
                    closeCardLiveData.value = it.second!!
                }
                CardEvent.HIDE->{
                    hideCardLiveData.value = it.second!!
                }
                CardEvent.NEXT->{
                    nextPartLiveData.value = it.second!!
                }
                CardEvent.ATTEMPT->{
                    changeAttemptLiveData.value = it.second!!
                }
                CardEvent.GAME_OVER->{
                    gameOverLiveData.value = it.second!!
                }
                CardEvent.END_PART->{
                    endPartLiveData.value = Unit
                }
                CardEvent.CORRECT1->{
                    correctCase1LiveData.value = it.second!!
                }
                CardEvent.CORRECT2->{
                    correctCase2LiveData.value = it.second!!
                }
                CardEvent.WRONG->{
                    wrongCaseLiveData.value = it.second!!
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun setLevel(level: LevelEnum) {
        gameUseCase.setLevel(level)
    }

    override fun getAttempt() {
        changeAttemptLiveData.value = gameUseCase.getAttempt()
    }

    override fun reloadGame() {
        gameUseCase.reloadGame()
    }
}