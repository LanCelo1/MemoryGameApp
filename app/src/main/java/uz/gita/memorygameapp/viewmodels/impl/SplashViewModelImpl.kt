package uz.gita.memorygameapp.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygameapp.viewmodels.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(

) : ViewModel(),SplashViewModel {
    override var openNextScreenLiveData = MutableLiveData<Unit>()
    override fun openNextScreen() {
        viewModelScope.launch {
            delay(1000)
            openNextScreenLiveData.postValue(Unit)
        }
    }
}