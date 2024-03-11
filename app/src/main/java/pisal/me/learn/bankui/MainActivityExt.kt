package pisal.me.learn.bankui

import android.content.pm.PackageManager
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.app.ActivityCompat
import androidx.core.view.isGone
import pisal.me.learn.bankui.common.blur.BlurLayout
import pisal.me.learn.bankui.common.extension.hide
import pisal.me.learn.bankui.common.extension.show

fun MainActivity.setNavigationBackgroundColor(@ColorRes colorResId: Int){
    window.navigationBarColor = resources.getColor(colorResId, theme)
}

fun MainActivity.setStatusBarColor(@ColorRes colorResId: Int){
    window.statusBarColor = resources.getColor(colorResId, theme)
}

fun MainActivity.setToolbarBackgroundColor(@ColorRes colorResId: Int){
    binding.appBarMain.toolbar.setBackgroundColor(resources.getColor(colorResId, theme))
}

fun MainActivity.hideToolbar() {
    binding.appBarMain.toolbar.hide()
}

fun MainActivity.showToolbar() {
    binding.appBarMain.toolbar.show()
}


fun MainActivity.showBlur() {
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
         findViewById<View>(R.id.app_bar_main)?.run {
             val blurEffect = RenderEffect.createBlurEffect(100f, 100f, Shader.TileMode.MIRROR)
             setRenderEffect(blurEffect)
         }
    } else {
        findViewById<BlurLayout>(R.id.blur_view)?.show()
    }
}

fun MainActivity.hideBlur() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        findViewById<View>(R.id.app_bar_main)?.run {
            setRenderEffect(null)
        }
    } else {
        findViewById<BlurLayout>(R.id.blur_view)?.hide()
    }
}

fun MainActivity.hideBlurIfNotLoading() {
    if (findViewById<BlurLayout>(R.id.loading).isGone) {
        hideBlur()
    }
}

fun MainActivity.showLoading() {
    findViewById<BlurLayout>(R.id.loading)?.show()
}

fun MainActivity.hideLoading() {
    findViewById<BlurLayout>(R.id.loading)?.hide()
}

fun MainActivity.showActionBar() {
    supportActionBar?.show()
}

fun MainActivity.hideActionBar() {
    supportActionBar?.hide()
}

//fun MainActivity.setToolbarTitle(title: String) {
//    findViewById<TextView>(R.id.toolbar_title)?.run {
//        text = title
//    }
//}

fun MainActivity.safeRunOnUiThread(after: Long, doSth: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        try {
            runOnUiThread {
                doSth()
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }, after)
}

fun MainActivity.safeRunOnUiThread(doSth: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        try {
            runOnUiThread {
                doSth()
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}

fun MainActivity.isPermissionGrated(permission: Permission): Boolean {
    val permissionState =
        ActivityCompat.checkSelfPermission(this, permission.value)
    return permissionState == PackageManager.PERMISSION_GRANTED
}