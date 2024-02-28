package pisal.me.learn.bankui.ui.aba.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import pisal.me.learn.bankui.R
import pisal.me.learn.bankui.databinding.FragmentAbaHomeBinding
import pisal.me.learn.bankui.setNavigationBackgroundColor
import pisal.me.learn.bankui.setStatusBarColor
import pisal.me.learn.bankui.setToolbarBackgroundColor
import pisal.me.learn.bankui.ui.withMainActivity

class AbaHomeFragment : Fragment() {

    private val viewModel by inject<AbaHomeViewModel>()
    private lateinit var binding: FragmentAbaHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentAbaHomeBinding.inflate(inflater, container, false).run {
            binding = this
            return  root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenus()
    }

    private fun setupMenus() {
        viewModel.gridMenus(requireContext()).observe(viewLifecycleOwner) {
            binding.gridMenus = it
        }
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setStatusBarColor(R.color.aba_primary)
            setNavigationBackgroundColor(R.color.aba_accent)
            setToolbarBackgroundColor(R.color.aba_primary)
        }
    }
}