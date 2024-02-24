package pisal.me.learn.bankui.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pisal.me.learn.bankui.data.repository.BankRetrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://pisal.me/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<BankRetrofit> {
        get<Retrofit>().create(BankRetrofit::class.java)
    }
}