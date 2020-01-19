package br.com.arthur.clubedoursolao.view.splash

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.LoginActivity
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import br.com.arthur.clubedoursolao.BuildConfig
import br.com.arthur.clubedoursolao.firebase.RemoteConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity() {

    val splashViewModel: SplashViewModel by viewModel()
    //    var cont = 0
    private val WAIT_TIME = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.checkHealth()
        splashViewModel.messageError.observe(this, Observer {
            if (it == "") {
                RemoteConfig.remoteConfigFetch()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            RemoteConfig.getFirebaseRemoteConfig().activateFetched()
                            val minVersionApp = RemoteConfig.getFirebaseRemoteConfig()
                                .getLong("min_version_app")
                                .toInt()
                            if (minVersionApp <= BuildConfig.VERSION_CODE) {
                                showSplash()
                            } else {
                                Toast.makeText(this, "Version", Toast.LENGTH_LONG).show()
                                showAlertMinVersion()
                            }
                        } else
                            showSplash()
                    }
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun showAlertMinVersion() {
        AlertDialog.Builder(this)
            .setTitle("Ops")
            .setMessage("Você esta utilizando uma versão muito antiga do aplicativo. Deseja atualizar?")
            .setPositiveButton("Sim") { dialog, which ->
                var intent: Intent
                try {
                    intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                    startActivity(intent)
                } catch (e: android.content.ActivityNotFoundException) {
                    intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                    startActivity(intent)
                }
            }
            .setNegativeButton("Não") { dialog, which ->
                finish()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
//
//    private fun continueApp(){
//
////
////        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
////        val isFirstOpen = preferences.getBoolean("open_first", true)
////        if (isFirstOpen) {
////            showLogin()
////        } else {
////            showSplash()
////        }
//    }

    fun showSplash() {

        Handler().postDelayed({
            showLogin()
        }, WAIT_TIME)

//        Thread(Runnable {
//            cont++
//
//            try {
//                while (cont === 1 || cont <= 5) {
//                    Thread.sleep(1000)
//                    cont++
//                }
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//
//            if (cont === 6) {
//                showLogin()
//                cont++
//            }
//        }).start()
    }

    fun showLogin() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
