package pisal.me.learn.bankui.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import pisal.me.learn.bankui.data.model.entity.Bank
import pisal.me.learn.bankui.data.model.entity.BankDiff
import pisal.me.learn.bankui.databinding.ItemBankBinding

class BankListAdapter : ListAdapter<Bank, BankListAdapter.ViewHolder>(BankDiff) {

    private var _itemClickListener: ((item: Bank) -> Unit)? = null
    fun onItemClicked(listener: (item: Bank) -> Unit) {
        _itemClickListener = listener
    }

    inner class ViewHolder(
        private val binding: ItemBankBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: Bank) {
            binding.item = item
            Glide.with(itemView.context)
                .load("https://pisal.me/storage/${item.logo}")
                .into(binding.imgLogo)
            binding.executePendingBindings()
            binding.card.setOnClickListener {
                _itemClickListener?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBankBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}