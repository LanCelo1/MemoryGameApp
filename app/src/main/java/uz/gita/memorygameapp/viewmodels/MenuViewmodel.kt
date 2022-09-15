package uz.gita.memorygameapp.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.memorygameapp.utils.LevelEnum

interface MenuViewmodel {
    fun openGameScreen(levelEnum: LevelEnum)
    fun openGameTwoPlayer()
    val openGameScreenLiveData: LiveData<LevelEnum>
    val openGameTwoPlayerLiveData: LiveData<Unit>
}