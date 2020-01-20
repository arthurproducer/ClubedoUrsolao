package br.com.arthur.clubedoursolao.repository

import br.com.arthur.clubedoursolao.model.LendingProduct
import br.com.arthur.clubedoursolao.model.Product
import br.com.arthur.clubedoursolao.model.TokenResponse
import br.com.arthur.clubedoursolao.model.User

interface AuthRepository {
    fun checkHealth(
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun checkAuth(
        user: User,
        onComplete: (TokenResponse?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun registerUser(
        user: User,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    )
}