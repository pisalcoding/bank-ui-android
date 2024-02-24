package pisal.me.learn.bankui.data.model.dto

import java.io.Serializable

data class ResponseWrapper<T>(
    val data: T?,
    val code: Int? = null,
    val message: String? = null,
    val success: Boolean = true
): Serializable