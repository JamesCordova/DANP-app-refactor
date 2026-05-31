package com.aero.refactorapp.di

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.MemorySessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://dukiljeypspkmjbqmeah.supabase.co",
            supabaseKey = "sb_publishable_Py-S-bHCz4BaFTcIaW-IKg_opwYmh37"
        ) {
            install(Postgrest)
            install(Auth) {
                sessionManager = MemorySessionManager()
            }
        }
    }
}
