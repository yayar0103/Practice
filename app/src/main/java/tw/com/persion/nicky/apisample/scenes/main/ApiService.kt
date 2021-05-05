package tw.com.persion.nicky.apisample.scenes.main

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    companion object{
        private const val URL = " https://syscode.com.tw/conre/api/"

        private val oKHttpClient = OkHttpClient.Builder()
            .addInterceptor(getHttpLogginInterceptor())
            .connectTimeout(10*1000, TimeUnit.MILLISECONDS)
            .readTimeout(10*1000, TimeUnit.MILLISECONDS)
            .writeTimeout(10*1000, TimeUnit.MILLISECONDS)
            .build()

        val Api: IApiService by lazy(LazyThreadSafetyMode.NONE) {
            val gson: Gson = GsonBuilder()
                .create()

            Retrofit.Builder()
                .client(oKHttpClient)
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(IApiService::class.java)
        }

        private fun getHttpLogginInterceptor(): HttpLoggingInterceptor {
            val loggin = HttpLoggingInterceptor { message -> Log.d("message", message) }
            loggin.level = HttpLoggingInterceptor.Level.BODY
            return loggin
        }
    }
}