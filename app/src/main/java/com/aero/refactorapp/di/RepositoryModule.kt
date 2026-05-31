package com.aero.refactorapp.di

import com.aero.refactorapp.data.repository.CartRepositoryImpl
import com.aero.refactorapp.data.repository.ProductRepositoryImpl
import com.aero.refactorapp.domain.repository.CartRepository
import com.aero.refactorapp.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository
}
