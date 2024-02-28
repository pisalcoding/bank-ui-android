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
import pisal.me.learn.bankui.ui.aba.AbaActivity

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
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcl.adapter = BankListAdapter().apply {
            viewModel.list().observe(viewLifecycleOwner) {
                when (it) {
                    is TResult.Success -> {
                        submitList(it.data)
                    }

                    is TResult.Failure -> {
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

    private fun openBankUi(bank: Bank) {
        when (bank.code) {
            AbaActivity.BANK_CODE -> {
                findNavController().navigate(R.id.action_nav_home_to_aba)
            }
            else -> {
                Alerter.error()
                    .withTitle("Not found!")
                    .withMessage("Sorry the UI of this bank is not available yet.")
                    .show(childFragmentManager, "HomeFragment")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}