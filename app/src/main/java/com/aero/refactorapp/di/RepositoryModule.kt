package com.aero.refactorapp.di

import com.aero.refactorapp.data.remote.ProductRemoteDataSource
import com.aero.refactorapp.data.remote.ProductRemoteDataSourceImpl
import com.aero.refactorapp.data.repository.CartRepositoryImpl
import com.aero.refactorapp.data.repository.ProductRepositoryImpl
import com.aero.refactorapp.data.repository.ThemeRepositoryImpl
import com.aero.refactorapp.domain.repository.CartRepository
import com.aero.refactorapp.domain.repository.ProductRepository
import com.aero.refactorapp.domain.repository.ThemeRepository
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

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository

    @Binds
    @Singleton
    abstract fun bindProductRemoteDataSource(
        productRemoteDataSourceImpl: ProductRemoteDataSourceImpl
    ): ProductRemoteDataSource
}
