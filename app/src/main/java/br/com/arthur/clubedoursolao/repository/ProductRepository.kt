package br.com.arthur.clubedoursolao.repository

import br.com.arthur.clubedoursolao.model.*


interface ProductRepository {

    fun getForCategory(
        category : String,
        onComplete: (List<Product>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun getMyProduct(
        user: User,
        onComplete: (List<LendingProduct>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun updateMyProduct(
        lendingProduct: LendingProduct,
        onComplete: (LendingProduct?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun registerMyNewProduct(
        product: InsertProduct,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun getMyDevolutionProducts(
        user: User,
        onComplete: (List<LendingProduct>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun returnDevolutionProduct(
        devolutionResponse: DevolutionResponse,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    )

}