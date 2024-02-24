package pisal.me.learn.bankui.di

import org.koin.dsl.module
import pisal.me.learn.bankui.data.repository.BankRepository
import pisal.me.learn.bankui.data.repository.IBankRepository

val dataModule = module {
    single<IBankRepository>{
        BankRepository(get())
    }
}