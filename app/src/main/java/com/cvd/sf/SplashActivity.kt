package com.cvd.sf

import android.content.Intent
import androidx.core.content.ContextCompat
import com.daimajia.androidanimations.library.Techniques
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By fahmi on 22/03/20
 */

class SplashActivity : AwesomeSplash() {
    override fun initSplash(configSplash: ConfigSplash) {

        configSplash.backgroundColor = R.color.colorPrimary
        configSplash.animCircularRevealDuration = 0
        configSplash.revealFlagX = Flags.REVEAL_RIGHT  //or Flags.REVEAL_LEFTs
        configSplash.revealFlagY = Flags.REVEAL_BOTTOM //or Flags.REVEAL_TOP

        configSplash.logoSplash = R.mipmap.ic_corona
        configSplash.animLogoSplashTechnique = Techniques.BounceIn

        configSplash.titleSplash = "Kawal Korona"
        configSplash.titleTextColor = R.color.white
        configSplash.titleTextSize = 30f //float value
        configSplash.animTitleDuration = 1200
        configSplash.animTitleTechnique = Techniques.SlideInDown

    }

    override fun animationsFinished() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}