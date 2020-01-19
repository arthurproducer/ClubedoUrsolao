package br.com.arthur.clubedoursolao.util

import android.app.Activity
import br.com.arthur.clubedoursolao.LoginActivity
import br.com.arthur.clubedoursolao.MainActivity
import br.com.arthur.clubedoursolao.view.registerUser.RegisterUserFragment
import br.com.arthur.clubedoursolao.view.splash.SplashActivity

class ScreenMap {
    companion object {
        val SCREEN_NOT_TRACKING = "SCREEN_NOT_TRACKING"
        private fun getScreenNameBy(className: String): String {
            val screensNames = getScreenNames()
            return if (screensNames[className] == null) SCREEN_NOT_TRACKING else screensNames[className]!!
        }

        fun getScreenNameBy(activty: Activity): String {
            return getScreenNameBy(activty::class.java.simpleName)
        }

        fun getClassName(screenName: String): String {
            val screenNames = getScreenNames()
            for (o in screenNames.keys) {
                if (screenNames[o] == screenName) {
                    return o
                }
            }
            return ""
        }

        private fun getScreenNames(): Map<String, String> {
            return mapOf(
//Login
                Pair(MainActivity::class.java.simpleName,"Entrando_no_App"),
                Pair(LoginActivity::class.java.simpleName, "Login_Usuario"),
                Pair(RegisterUserFragment::class.java.simpleName, "Criacao_Usuario"),
                Pair(SplashActivity::class.java.simpleName, "Splash")
            )
        }
    }
}