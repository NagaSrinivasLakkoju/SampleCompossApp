package com.example.samplecompossapp.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.samplecompossapp.di.NetworkModule.Companion.NETWORK_REQUEST_TIMEOUT_SECONDS
import com.example.samplecompossapp.network.ApiService
import com.example.samplecompossapp.utils.AppConstants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient)=
         ApolloClient.Builder()
           // .serverUrl("https://countries.trevorblades.com/graphql")
            .serverUrl("https://nhmemberrefreshservice-nb-v2.azurewebsites.net/graphql")
             .okHttpClient(okHttpClient)
            .build() 



    @Provides
    @Singleton
     fun getOkHttpClient() :OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }
    companion object {
            const val NETWORK_REQUEST_TIMEOUT_SECONDS = 15L
            const val BASE_URL = "https://singbaeapi.azurewebsites.net/"
        }
}