package pisal.me.learn.bankui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.pisal.alerter.Alerter
import org.koin.android.ext.android.inject
import pisal.me.learn.bankui.R
import pisal.me.learn.bankui.common.TResult
import pisal.me.learn.bankui.data.model.entity.Bank
import pisal.me.learn.bankui.databinding.FragmentHomeBinding
import pisal.me.learn.bankui.setNavigationBackgroundColor
import pisal.me.learn.bankui.setStatusBarColor
import pisal.me.learn.bankui.setToolbarBackgroundColor
import pisal.me.learn.bankui.ui.aba.home.AbaHomeFragment
import pisal.me.learn.bankui.ui.acleda.home.AcledaHomeFragment
import pisal.me.learn.bankui.ui.withMainActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshList()
        setupSwipeRefresh()
    }

    private fun openBankUi(bank: Bank) {
        when (bank.code) {
            AbaHomeFragment.BANK_CODE -> {
                findNavController().navigate(R.id.action_nav_home_to_aba)
            }
            AcledaHomeFragment.BANK_CODE -> {
                findNavController().navigate(R.id.action_nav_home_to_acleda)
            }

            else -> {
                Alerter.error()
                    .withTitle("Not found!")
                    .withMessage("Sorry the UI of this bank is not available yet.")
                    .show(childFragmentManager, "HomeFragment")
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            refreshList()
        }
    }

    private fun refreshList() {
        binding.rcl.adapter = BankListAdapter().apply {
            binding.swipeRefresh.isRefreshing = true
            viewModel.list().observe(viewLifecycleOwner) {
                when (it) {
                    is TResult.Success -> {
                        binding.swipeRefresh.isRefreshing = false
                        submitList(it.data)
                    }

                    is TResult.Failure -> {
                        binding.swipeRefresh.isRefreshing = false
                        Alerter.error()
                            .withTitle("Error")
                            .withMessage(it.message ?: "Unknown error!")
                            .show(childFragmentManager, "HomeFragment")
                    }
                }
            }

            onItemClicked {
                openBankUi(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setStatusBarColor(R.color.primary)
            setToolbarBackgroundColor(R.color.primary)
            setNavigationBackgroundColor(R.color.white)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}