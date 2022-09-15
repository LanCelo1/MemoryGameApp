package uz.gita.memorygameapp.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.viewmodels.MenuViewmodel
import javax.inject.Inject

@HiltViewModel
class MenuViewModelImpl @Inject constructor() : ViewModel(),MenuViewmodel {
    override var openGameScreenLiveData = MutableLiveData<LevelEnum>()
    override var openGameTwoPlayerLiveData = MutableLiveData<Unit>()
    override fun openGameScreen(levelEnum: LevelEnum) {
        openGameScreenLiveData.value = levelEnum
    }

    override fun openGameTwoPlayer() {
        openGameTwoPlayerLiveData.value = Unit
    }
}