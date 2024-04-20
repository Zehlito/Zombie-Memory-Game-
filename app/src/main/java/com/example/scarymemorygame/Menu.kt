package com.example.scarymemorygame



import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.scarymemorygame.databinding.ActivityMenuBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

private lateinit var mediaPlayer: MediaPlayer
private lateinit var mAdView: AdView

class Menu : AppCompatActivity() {


    private lateinit var binding: ActivityMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadBannerAd()

        //esconde a barra
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)




        binding.btn1.setOnClickListener {
            //soundPlayer.playSound()
            tocarCarta()
            val irParaLevelEasy = Intent(this, LevelEasy::class.java)
            startActivity(irParaLevelEasy)

        }

        binding.tv3.setOnClickListener {
            tocarCarta()
            val irParaLevelMedium = Intent(this,Level_Medium::class.java)
            startActivity(irParaLevelMedium)

        }

        binding.tv4.setOnClickListener {
            tocarCarta()
            val irParaLevelLarge = Intent(this, Level_Large::class.java)
            startActivity(irParaLevelLarge)
        }

        }

    private fun tocarCarta(){
        mediaPlayer = MediaPlayer.create(this, R.raw.cardsound)
        mediaPlayer.start()
    }
    private fun loadBannerAd() {

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object : AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.

            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                //Toast.makeText(this@LevelEasy, "Ad cloased", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                //Toast.makeText(this@LevelEasy, "Ad Loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }


    }


}

