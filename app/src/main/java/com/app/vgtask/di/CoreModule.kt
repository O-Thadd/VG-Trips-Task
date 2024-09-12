package com.app.vgtask.di

import android.content.Context
import com.app.vgtask.VGTaskApplication
import com.app.vgtask.data.dataSources.remote.CITIES_BASE_URL
import com.app.vgtask.data.dataSources.remote.CitiesRemoteService
import com.app.vgtask.data.dataSources.remote.USERS_BASE_URL
import com.app.vgtask.data.dataSources.remote.UsersRemoteService
import com.app.vgtask.data.dataSources.remote.client
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun provideVGTaskApp(context: Context): VGTaskApplication {
        return context as VGTaskApplication
    }

    @Provides
    fun providesUsersRemoteService(): UsersRemoteService {

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(USERS_BASE_URL)
            .client(client)
            .build()

        val remoteService: UsersRemoteService by lazy {
            retrofit.create(UsersRemoteService::class.java)
        }

        return remoteService
    }

    @Provides
    fun providesCitiesRemoteService(): CitiesRemoteService {

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CITIES_BASE_URL)
            .client(client)
            .build()

        val remoteService: CitiesRemoteService by lazy {
            retrofit.create(CitiesRemoteService::class.java)
        }

        return remoteService
    }
}