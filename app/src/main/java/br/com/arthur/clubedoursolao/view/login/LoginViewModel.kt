package br.com.arthur.clubedoursolao.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.api.AuthInterceptor
import br.com.arthur.clubedoursolao.model.TokenResponse
import br.com.arthur.clubedoursolao.model.User
import br.com.arthur.clubedoursolao.repository.AuthRepository

class LoginViewModel(val authRepository: AuthRepository) : ViewModel() {

    val messageError = MutableLiveData<String>()
    val token : MutableLiveData<TokenResponse> = MutableLiveData()


    fun checkAuth(user: User) {
        authRepository.checkAuth(user, {
            token.value = it
            messageError.value = "Dados gravados com sucesso"
        }, {
            messageError.value = it?.message
        }
        )
    }
}
