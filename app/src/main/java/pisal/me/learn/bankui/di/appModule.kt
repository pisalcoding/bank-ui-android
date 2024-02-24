package pisal.me.learn.bankui.di

import io.bloco.faker.Faker
import org.koin.dsl.module

val appModule = module {
    single { Faker() }
}
