package pisal.me.learn.bankui.data.repository

import pisal.me.learn.bankui.common.TResult
import pisal.me.learn.bankui.data.model.entity.Bank

interface IBankRepository {
    suspend fun list(page: Long, size: Long): TResult<List<Bank>>
}