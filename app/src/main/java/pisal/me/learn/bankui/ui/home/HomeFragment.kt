package pisal.me.learn.bankui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.pisal.alerter.Alerter
import org.koin.android.ext.android.inject
import pisal.me.learn.bankui.common.TResult
import pisal.me.learn.bankui.databinding.FragmentHomeBinding

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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}