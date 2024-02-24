package pisal.me.learn.bankui.data.model.entity

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import java.io.Serializable

data class Bank(
    val id: Int,
    val name: String,
    val description: String,
    val logo: String,
    val active: Int,
    val github: String? = null,
) : Serializable

object BankDiff : ItemCallback<Bank>() {
    override fun areItemsTheSame(oldItem: Bank, newItem: Bank): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Bank, newItem: Bank): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }

}
