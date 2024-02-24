package pisal.me.learn.bankui.data.repository

import pisal.me.learn.bankui.common.TResult
import pisal.me.learn.bankui.common.safeApiCall
import pisal.me.learn.bankui.data.model.entity.Bank


class BankRepository(private val retrofit: BankRetrofit) : IBankRepository {

    override suspend fun list(page: Long, size: Long): TResult<List<Bank>> {
        return safeApiCall {
            retrofit.list(page, size)
        }
    }
}