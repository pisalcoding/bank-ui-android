package pisal.me.learn.bankui.ui.acleda.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import pisal.me.learn.bankui.R
import pisal.me.learn.bankui.data.model.dto.MenuModel

class AcledaHomeViewModel : ViewModel() {

    fun gridMenus(context: Context) = liveData {
        emit(
            listOf(
                MenuModel(
                    "1",
                    "Payments",
                    R.drawable.ic_payments_home_menu
                ),
                MenuModel("2", "Mobile Top-up", R.drawable.ic_cards_home_menu),
                MenuModel(
                    "3",
                    "Transfers",
                    R.drawable.ic_transfers_home_menu
                ),
                MenuModel(
                    "4",
                    "Pay-Me",
                    R.drawable.ic_new_account_home_menu
                ),
                MenuModel(
                    "5",
                    "Scan QR",
                    R.drawable.ic_scan_qr_home_menu
                ),
                MenuModel(
                    "6",
                    "Accounts",
                    R.drawable.ic_accounts_home_menu
                ),
                MenuModel(
                    "7",
                    "Deposits",
                    R.drawable.ic_scan_qr_home_menu
                ),
                MenuModel("8", "Loans", R.drawable.ic_loans_home_menu),
                MenuModel(
                    "9",
                    "Quick Cash",
                    R.drawable.ic_services_home_menu
                ),
            )
        )
    }
}