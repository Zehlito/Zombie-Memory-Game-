package com.example.scarymemorygame

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager


private lateinit var mediaPlayer: MediaPlayer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //trocar a cor da barra
        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT

        mediaPlayer = MediaPlayer.create(this, R.raw.zombie_pain)
        mediaPlayer.start()




        //inicia a tela com tempo de 1,5 sec.
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,Menu::class.java)
            startActivity(intent)
            finish()
        },1500)
    }
}