package com.app.vgtask.data.dataSources.remote

import com.app.vgtask.data.models.City
import com.app.vgtask.data.models.User
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.HashMap
import java.util.Objects

const val USERS_BASE_URL = "https://ca587c6ffc4c99c6d454.free.beeceptor.com"
const val CITIES_BASE_URL = "https://cadc90082544c75a2ffd.free.beeceptor.com"

val interceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

interface UsersRemoteService {
    @POST("api/users")
    suspend fun createUser(@Body body: String = Gson().toJson(HashMap<String, String>())): User

    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") userId: String): User

    @GET("api/users/{id}")
    suspend fun updateUser(@Path("id") userId: String, @Body user: User)
}

interface CitiesRemoteService {
    @GET("api/cities")
    suspend fun getCities(): List<City>
}