package uz.gita.memorygameapp.ui.screen

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.databinding.ScreenMenuBinding
import uz.gita.memorygameapp.viewmodels.MenuViewmodel
import uz.gita.memorygameapp.viewmodels.impl.MenuViewModelImpl


@AndroidEntryPoint
class MenuScreen : Fragment(R.layout.screen_menu) {
    val viewModel : MenuViewmodel by viewModels<MenuViewModelImpl>()
    val binding by viewBinding(ScreenMenuBinding::bind)
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mediaPlayer = MediaPlayer.create(requireContext(),R.raw.click)
        binding.apply {
            easy.setOnClickListener { viewModel.openGameScreen(LevelEnum.EASY) }
            medium.setOnClickListener { viewModel.openGameScreen(LevelEnum.MEDIUM) }
            hard.setOnClickListener  { viewModel.openGameScreen(LevelEnum.HARD) }
            twoplayer.setOnClickListener { viewModel.openGameTwoPlayer() }
        }
        viewModel.openGameScreenLiveData.observe(this,openGameScreenLiveDataObserver)
        viewModel.openGameTwoPlayerLiveData.observe(this,openGameTwoPlayerObserver)
    }
    val openGameScreenLiveDataObserver = Observer<LevelEnum>{
        mediaPlayer.start()
        findNavController().navigate(MenuScreenDirections.actionMenuScreenToGameScreen(
            it
        ))
    }
    val openGameTwoPlayerObserver = Observer<Unit>{
        mediaPlayer.start()
        findNavController().navigate(MenuScreenDirections.actionMenuScreenToMenuTwoPlayerScreen(LevelEnum.MULTIPLAYER))
    }
}