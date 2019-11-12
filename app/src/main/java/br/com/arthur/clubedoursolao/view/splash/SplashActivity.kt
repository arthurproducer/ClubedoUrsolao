package br.com.arthur.clubedoursolao.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.arthur.clubedoursolao.R
import br.com.arthur.clubedoursolao.LoginActivity
import android.content.Intent

class SplashActivity : AppCompatActivity() {

    //val  splashViewModel: SplashViewModel by viewModel()
    var cont = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniciaSplash()
    }

    fun iniciaSplash() {

        setContentView(R.layout.activity_splash)

        Thread(Runnable {
            cont++

            try {
                while (cont === 1 || cont <= 5) {
                    Thread.sleep(1000)
                    cont++
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (cont === 6) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
                cont++
            }
        }).start()

    }
}
