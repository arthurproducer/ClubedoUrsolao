package br.com.arthur.clubedoursolao.view.registerUser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthur.clubedoursolao.model.TokenResponse
import br.com.arthur.clubedoursolao.model.User
import br.com.arthur.clubedoursolao.repository.AuthRepository

class RegisterUserViewModel(val authRepository: AuthRepository) : ViewModel() {

    val messageError = MutableLiveData<String>()
//    val token : MutableLiveData<TokenResponse> = MutableLiveData()

    fun registerUser(user: User) {
        authRepository.registerUser(user, {
            messageError.value = "Usuário registrado com sucesso"
        }, {
            messageError.value = it?.message
        }
        )
    }
}