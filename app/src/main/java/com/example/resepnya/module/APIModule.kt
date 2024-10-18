package com.example.resepnya.module

import com.example.resepnya.datasource.IRecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Singleton
    @Provides
    fun providerRecipeService(retrofit: Retrofit) : IRecipeService {
        return retrofit.create(IRecipeService::class.java)
    }

}