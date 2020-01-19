package br.com.arthur.clubedoursolao.common

import androidx.appcompat.app.AppCompatActivity
import br.com.arthur.clubedoursolao.util.ClubedoUrsolaoTracker
import br.com.arthur.clubedoursolao.util.ScreenMap

open class BaseActivity : AppCompatActivity() {
    open fun getScreenName(): String {
        return ScreenMap.getScreenNameBy(this)
    }
    override fun onStart() {
        super.onStart()
        ClubedoUrsolaoTracker.trackScreen(this, getScreenName())
    }
}