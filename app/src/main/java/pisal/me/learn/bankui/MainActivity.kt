package pisal.me.learn.bankui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import pisal.me.learn.bankui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    /////////////////////////////////////////
    //#region Permissions
    /////////////////////////////////////////
    private val permissionHandlers: HashMap<String, ((success: Boolean) -> Unit)> = hashMapOf()
    fun requestPermissionThen(
        permission: Permission,
        onSuccess: () -> Unit,
        onDenied: (() -> Unit)? = null,
    ) {
        val handler: (success: Boolean) -> Unit = { success ->
            if (success) {
                onSuccess()
            } else {
                onDenied?.invoke()
            }
        }
        permissionHandlers[permission.value] = handler
        if (!isPermissionGrated(permission)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                permission.requestCode
            )
        } else {
            handler.invoke(true)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()) {
            permissionHandlers.forEach {
                if (permissions.contains(it.key)) {
                    it.value.invoke(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                }
            }
        }
    }
    //#endregion
}

enum class Permission(val value: String, val requestCode: Int) {
    CAMERA(Manifest.permission.CAMERA, 111)
}