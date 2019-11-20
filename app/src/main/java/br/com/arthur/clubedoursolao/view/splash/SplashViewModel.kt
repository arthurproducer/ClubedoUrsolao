package br.com.arthur.clubedoursolao.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.repository.AuthRepository

class SplashViewModel(val  auth: AuthRepository) : ViewModel(){
    val messageError: MutableLiveData<String> = MutableLiveData()
    fun checkHealth() {
        auth.checkHealth(
            onComplete = {
                messageError.value = ""
            },
            onError = {
                messageError.value = it?.message
            }
        )
    }
}
