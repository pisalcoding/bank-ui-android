package pisal.me.learn.bankui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pisal.me.learn.bankui.ui.home.HomeViewModel

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}