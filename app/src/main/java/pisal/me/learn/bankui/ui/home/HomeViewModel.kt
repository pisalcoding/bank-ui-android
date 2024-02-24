package pisal.me.learn.bankui.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import pisal.me.learn.bankui.data.repository.IBankRepository

class HomeViewModel(private val repo: IBankRepository) : ViewModel() {

    fun list(page: Long = 0, size: Long = 100) = liveData {
        emit(repo.list(page, size))
    }

}