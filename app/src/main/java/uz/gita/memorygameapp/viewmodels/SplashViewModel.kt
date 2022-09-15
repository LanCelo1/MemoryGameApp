package uz.gita.memorygameapp.viewmodels

import androidx.lifecycle.LiveData

interface SplashViewModel  {
    fun openNextScreen()
    val openNextScreenLiveData: LiveData<Unit>
}