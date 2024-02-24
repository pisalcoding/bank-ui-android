package pisal.me.learn.bankui.data.repository

import androidx.annotation.WorkerThread
import pisal.me.learn.bankui.data.model.entity.Bank
import retrofit2.http.GET
import retrofit2.http.Query

@WorkerThread
interface BankRetrofit {

    @GET("/api/banks")
    suspend fun list(@Query("page") page: Long, @Query("size") size: Long): List<Bank>
}