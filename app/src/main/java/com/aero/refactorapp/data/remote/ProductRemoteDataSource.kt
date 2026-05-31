package com.aero.refactorapp.data.remote

import com.aero.refactorapp.data.remote.dto.CategoryDto
import com.aero.refactorapp.data.remote.dto.ProductDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject

interface ProductRemoteDataSource {
    suspend fun getProducts(): List<ProductDto>
    suspend fun getCategories(): List<CategoryDto>
}

class ProductRemoteDataSourceImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ProductRemoteDataSource {

    override suspend fun getProducts(): List<ProductDto> {
        return supabaseClient.postgrest["Products"].select().decodeList<ProductDto>()
    }

    override suspend fun getCategories(): List<CategoryDto> {
        return supabaseClient.postgrest["ProductCategories"].select().decodeList<CategoryDto>()
    }
}
