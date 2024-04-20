package com.example.scarymemorygame

import android.graphics.Color
import android.media.SoundPool
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scarymemorygame.databinding.ActivityLevelLargeBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

private lateinit var binding: ActivityLevelLargeBinding
private lateinit var soundPool: SoundPool
private var soundStreamCarta: Int = 0
private var soundsStreamWin: Int = 0
private var soundsStreamEnd: Int =0
private lateinit var mAdView: AdView

class Level_Large : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelLargeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadBannerAd()

        soundPool = SoundPool.Builder().build()

        val winPair = soundPool.load(this, R.raw.zombie_pain, 1)
        val endGame = soundPool.load(this, R.raw.female_scream, 1)
        val carta = soundPool.load(this, R.raw.cardsound, 1)

        val images: MutableList<Int> = mutableListOf(
            R.drawable.memory_1,
            R.drawable.zombie_1_2,
            R.drawable.zombie_2_1,
            R.drawable.zombie_3_1,
            R.drawable.zombie_4_1,
            R.drawable.zombie_5_1,
            R.drawable.zombie_6_1,
            R.drawable.zombie_8_1,
            R.drawable.zombie_9_1,
            R.drawable.zombie_10_1,
            R.drawable.zombie_11_1,
            R.drawable.zombie_12_1,
            R.drawable.zombie_13_1,
            R.drawable.zombie_14_1,
            R.drawable.zombie_15_1,
            R.drawable.zombie_16_1,
            R.drawable.zombie_17_1,
            R.drawable.zombie_18_1,
            R.drawable.zombie_19_1,
            R.drawable.zombie_20_1,
            R.drawable.zombie_21_1,
            R.drawable.memory_1,
            R.drawable.zombie_1_2,
            R.drawable.zombie_2_1,
            R.drawable.zombie_3_1,
            R.drawable.zombie_4_1,
            R.drawable.zombie_5_1,
            R.drawable.zombie_6_1,
            R.drawable.zombie_8_1,
            R.drawable.zombie_9_1,
            R.drawable.zombie_10_1,
            R.drawable.zombie_11_1,
            R.drawable.zombie_12_1,
            R.drawable.zombie_13_1,
            R.drawable.zombie_14_1,
            R.drawable.zombie_15_1,
            R.drawable.zombie_16_1,
            R.drawable.zombie_17_1,
            R.drawable.zombie_18_1,
            R.drawable.zombie_19_1,
            R.drawable.zombie_20_1,
            R.drawable.zombie_21_1
        )

        val buttons = arrayOf(
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9,
            binding.btn10,
            binding.btn11,
            binding.btn12,
            binding.btn13,
            binding.btn14,
            binding.btn15,
            binding.btn16,
            binding.btn17,
            binding.btn18,
            binding.btn19,
            binding.btn20,
            binding.btn21,
            binding.btn22,
            binding.btn23,
            binding.btn24,
            binding.btn25,
            binding.btn26,
            binding.btn27,
            binding.btn28,
            binding.btn29,
            binding.btn30,
            binding.btn31,
            binding.btn32,
            binding.btn33,
            binding.btn34,
            binding.btn35,
            binding.btn36,
            binding.btn37,
            binding.btn38,
            binding.btn39,
            binding.btn40,
            binding.btn41,
            binding.btn42
        )


        val cardBack = R.drawable.card_zombie
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var numero = 1
        var controle = 1




        images.shuffle()
        for (i in 0..41) {

            buttons[i].setBackgroundResource(cardBack)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {

                if (soundStreamCarta == 0) {
                    soundStreamCarta = soundPool.play(carta, 1F, 1F, 0, 0, 1F)
                    //Toast.makeText(this.applicationContext, " speaking...", Toast.LENGTH_SHORT).show()
                }
                else {
                    soundPool.pause(soundStreamCarta)
                    0
                    soundPool.release()
                }
                soundStreamCarta = 0




                binding.textView2.setText("$controle")
                controle++



                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])



                    if (clicked == 0) {
                        lastClicked = i


                    }
                    clicked++


                } else if (buttons[i].text !in "cardBack") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].text = "cardBack"
                    clicked--


                }

                if (clicked == 2) {
                    turnOver = true

                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnOver = false
                        clicked = 0

                        numero++


                        if (numero == 22) {
                            var zombie = numero - 1
                            if (soundsStreamEnd == 0) {
                                soundsStreamEnd = soundPool.play(endGame, 1F, 1F, 0, 0, 1F)

                            }
                            else {
                                soundPool.pause(soundsStreamEnd)
                                0
                            }
                            soundsStreamEnd = 0

                            binding.textView2.setText("Liberty $zombie Zombies!!!")
                            binding.textView2.setTextColor(Color.RED)


                        } else if (numero >= 2 && numero <= 21) {

                            if (soundsStreamWin == 0) {
                                soundsStreamWin = soundPool.play(winPair, 1F, 1F, 0, 0, 1F)

                            }
                            else {
                                soundPool.pause(soundsStreamWin)
                                0
                            }
                            soundsStreamWin = 0

                        }

                    }
                } else if (clicked == 0) {
                    turnOver = false


                }

            }
        }


    }


    private fun loadBannerAd() {

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.

            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                //Toast.makeText(this@Level_Large, "Ad cloased", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                //Toast.makeText(this@Level_Large, "Ad Loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }


    }
}
