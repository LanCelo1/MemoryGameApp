package uz.gita.memorygameapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var musicPlayer : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)

        musicPlayer = MediaPlayer.create(this,R.raw.back_music)
        musicPlayer.isLooping = true
    }

    override fun onStart() {
        super.onStart()
        musicPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        musicPlayer.pause()
    }

}