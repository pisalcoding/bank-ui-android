package pisal.me.learn.bankui.common

import pisal.me.learn.bankui.common.Currency
import pisal.me.learn.bankui.common.Currency.USD
import java.io.Serializable
import java.math.BigDecimal

data class Money(
    val amount: BigDecimal = BigDecimal.ZERO,
    val currency: String,
) : Serializable {

    fun appCurrency(): Currency {
        return Currency[currency] ?: USD
    }
}

