package uz.gita.memorygameapp.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.domain.useCase.AllCardDataUseCase
import uz.gita.memorygameapp.viewmodels.MenuTwoPlayerViewModel
import javax.inject.Inject

@HiltViewModel
class MenuTwoPlayerViewModelImpl @Inject constructor(
    private val useCase: AllCardDataUseCase
) : MenuTwoPlayerViewModel, ViewModel() {
    override fun getCardData() {
        useCase.getAllCards().onEach {
            getAllCardData.postValue(it)
        }.launchIn(viewModelScope)
    }

    override val getAllCardData = MutableLiveData<List<CardData>>()
}