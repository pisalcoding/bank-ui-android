package pisal.me.learn.bankui.ui

import androidx.fragment.app.Fragment
import pisal.me.learn.bankui.MainActivity

fun Fragment.withMainActivity(block: MainActivity.() -> Unit) {
    if (this.activity != null && this.activity is MainActivity) {
        block(requireActivity() as @ParameterName(name = "activity") MainActivity)
    }
}