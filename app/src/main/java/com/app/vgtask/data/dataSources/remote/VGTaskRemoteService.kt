package com.app.vgtask.data.dataSources.remote

import com.app.vgtask.data.models.City
import com.app.vgtask.data.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val BASE_URL = "https://cadc90082544c75a2ffd.free.beeceptor.com/api"

val interceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface VGTaskRemoteService {
    @POST("users")
    fun createUser(): User

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): User

    @GET("users/{id}")
    fun updateUser(@Path("id") userId: String, @Body user: User)

    @GET("cities")
    fun getCities(): List<City>
}

val remoteService: VGTaskRemoteService by lazy {
    retrofit.create(VGTaskRemoteService::class.java)
}