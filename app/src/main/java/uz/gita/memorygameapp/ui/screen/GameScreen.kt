package uz.gita.memorygameapp.ui.screen

import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.databinding.ScreenGameBinding
import uz.gita.memorygameapp.utils.scope
import uz.gita.memorygameapp.viewmodels.GameViewModel
import uz.gita.memorygameapp.viewmodels.impl.GameViewModelImpl

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    val viewModel: GameViewModel by viewModels<GameViewModelImpl>()
    val binding by viewBinding(ScreenGameBinding::bind)
    private val argument: GameScreenArgs by navArgs()
    private var count = 0
    private var _height = 0
    private var _width = 0
    private lateinit var level: LevelEnum
    private val cardList = ArrayList<ImageView>()
    private var isActive = true
    private lateinit var clickMediaPlayer : MediaPlayer
    private lateinit var correctMediaPlayer : MediaPlayer
    private lateinit var wrongMediaPlayer : MediaPlayer
    private lateinit var congratMediaPlayer : MediaPlayer
    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (argument.level == LevelEnum.MULTIPLAYER) {
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }else {
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        clickMediaPlayer = MediaPlayer.create(requireContext(),R.raw.click)
        correctMediaPlayer = MediaPlayer.create(requireContext(),R.raw.correct)
        congratMediaPlayer = MediaPlayer.create(requireContext(),R.raw.congrat)
        wrongMediaPlayer = MediaPlayer.create(requireContext(),R.raw.wrong_state2)
        mediaPlayer = MediaPlayer.create(requireContext(),R.raw.click)

        argument.level.apply {
            level = this
            count = x * y
            viewModel.setLevel(level)
        }

        main.post {
            if (level == LevelEnum.MULTIPLAYER){
                _height = (main.height / (level.y ))
                _width = (main.width / (level.x + 3))
            }else{
                _height = (main.height / (level.y + 3))
                _width = (main.width / (level.x + 1))
            }
            val lp = container.layoutParams
            lp.apply {
                height = _height * level.y
                width = _width * level.x
            }
            container.layoutParams = lp
            container.invalidate()
            loadViews()
            viewModel.getAllCardData(level)
        }

//        loadCards()
        setUpListeners()
        setUpObservers()
    }

    private fun setUpListeners() {
        binding.apply {
            nextLevel.setOnClickListener {
                mediaPlayer.start()
                binding.containerEndParts.visibility = View.GONE
                loadCards()
            }
            menuDialog.setOnClickListener {
                mediaPlayer.start()
                binding.containerEndParts.visibility = View.GONE
                findNavController().navigateUp()
            }

            btnNewGame.setOnClickListener {
                mediaPlayer.start()
                binding.containerGameOver.visibility = View.GONE
                findNavController().navigateUp()
            }
            menu?.setOnClickListener {
                mediaPlayer.start()
                findNavController().navigateUp()
            }
        }
    }

    private fun loadCards() {
        loadViews()
        viewModel.getAllCardData(level)
    }

    private fun setUpObservers() {
        viewModel.getAllCardDataLiveData.observe(viewLifecycleOwner, getAllCardObserver)
        viewModel.openCardLiveData.observe(viewLifecycleOwner, openCardObserver)
        viewModel.correctCaseLiveData.observe(viewLifecycleOwner, correctCaseObserver)
        viewModel.wrongCaseLiveData.observe(viewLifecycleOwner, wrongCaseObserver)
        viewModel.hideCardLiveData.observe(viewLifecycleOwner, hideCardObserver)
        viewModel.closeCardLiveData.observe(viewLifecycleOwner, closeCardObserver)
        viewModel.nextPartLiveData.observe(viewLifecycleOwner, nextPartObserver)
        viewModel.changeAttemptLiveData.observe(viewLifecycleOwner, changeAttemptObserver)
        viewModel.gameOverLiveData.observe(viewLifecycleOwner, gameOverObserver)
        viewModel.endPartLiveData.observe(viewLifecycleOwner, endPartObserver)

    }

    private fun loadViews() {
        viewModel.getAttempt()
        cardList.clear()
        binding.container.removeAllViews()
        for (i in 0 until level.x) {
            for (j in 0 until level.y) {
                val image = ImageView(requireContext())
                binding.container.addView(image)
                val lp = image.layoutParams as RelativeLayout.LayoutParams
                lp.apply {
                    height = _height
                    width = _width
                }
                image.x = i * _width * 1f
                image.y = j * _height * 1f
                image.scaleType = ImageView.ScaleType.FIT_XY
                lp.setMargins(4, 4, 4, 4)
                image.layoutParams = lp
                image.setImageResource(R.drawable.image_back)
                cardList.add(image)
            }
        }
    }

    val getAllCardObserver = Observer<List<CardData>> { list ->
        Timber.d("work keldi")
        for (i in 0 until list.size) {
            cardList[i].tag = list[i]
            cardList[i].setOnClickListener {
                Timber.d("work")
                viewModel.clickCard(i, list[i].amount)
            }
        }
    }

    private val openCardObserver = Observer<Int> {
       clickMediaPlayer.start()
        openCard(it)
    }
    private val closeCardObserver = Observer<Int> {
        closeCard(it)
    }
    private val correctCaseObserver = Observer<Unit> {
       /* if (mediaPlayer.isPlaying) mediaPlayer.stop()
        _mediaPlayer = MediaPlayer.create(requireContext(),R.raw.correct)
        mediaPlayer.start()*/
        correctMediaPlayer.start()
    }
    private val wrongCaseObserver = Observer<Unit> {
       /* if (mediaPlayer.isPlaying) mediaPlayer.stop()
        _mediaPlayer = MediaPlayer.create(requireContext(),R.raw.correct)
        mediaPlayer.start()*/
        wrongMediaPlayer.start()
    }
    private val hideCardObserver = Observer<Int> {
        hideCard(it)
    }
    private val nextPartObserver = Observer<Int> {
        binding.containerEndParts.visibility = View.VISIBLE
        binding.levelText?.text = "$it/10"
       /* if (mediaPlayer.isPlaying) mediaPlayer.stop()
        _mediaPlayer = MediaPlayer.create(requireContext(),R.raw.congrat)
        mediaPlayer.start()*/
        congratMediaPlayer.start()

    }
    private val changeAttemptObserver = Observer<Int> {
        binding.attempt?.text = it.toString()
    }
    private val gameOverObserver = Observer<Int> {
        binding.containerGameOver.visibility = View.VISIBLE
    }

    private val endPartObserver = Observer<Unit> {
        binding.containerGameOver.visibility = View.VISIBLE
        binding.btnContinue.text = "You Win!"
     /*   if (mediaPlayer.isPlaying) mediaPlayer.stop()
        _mediaPlayer = MediaPlayer.create(requireContext(),R.raw.congrat)
        mediaPlayer.start()*/
        congratMediaPlayer.start()
    }

    fun openCard(id: Int) {
        val card = cardList[id]
        card.isEnabled = false
        if (card.rotationY == 0f) {
            card.animate().setDuration(200)
                .rotationY(89f).withEndAction {
                    card.rotationY = 91f
                    (card as ImageView).setImageResource((cardList[id].tag as CardData).imgUrl)
                    card.animate().setDuration(200)
                        .rotationY(180f).withEndAction {
                            card.isEnabled = true
                        }
                }
        }
    }

    fun closeCard(id: Int) {
        val card = cardList[id]
        card.isEnabled = false
        card.animate().setDuration(200)
            .rotationY(91f).withEndAction {
                card.rotationY = 89f
                (card as ImageView).setImageResource(R.drawable.image_back)
                card.animate().setDuration(200)
                    .rotationY(0f).withEndAction {
                        card.isEnabled = true
                    }
            }
    }

    fun hideCard(id: Int) {
        cardList[id].visibility = View.INVISIBLE
    }
}
