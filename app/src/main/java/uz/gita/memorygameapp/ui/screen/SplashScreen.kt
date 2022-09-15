package uz.gita.memorygameapp.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.viewmodels.SplashViewModel
import uz.gita.memorygameapp.viewmodels.impl.SplashViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    val viewModel : SplashViewModel by viewModels<SplashViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextScreen()
        viewModel.openNextScreenLiveData.observe(viewLifecycleOwner,openNextScreenObserver)
    }
    val openNextScreenObserver = Observer<Unit>{
        findNavController().navigate(SplashScreenDirections.actionSplashScreenToMenuScreen())
    }
}