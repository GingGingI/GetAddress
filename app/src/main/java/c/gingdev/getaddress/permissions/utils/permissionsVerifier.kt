package c.gingdev.getaddress.permissions.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

open class permissionVerifier: AppCompatActivity() {
    private lateinit var activity: Activity
    private lateinit var permissionListener: permissionListener

    protected fun setActivity(activity: Activity) {
        this.activity = activity
    }

    protected fun setListener(listener: permissionListener) {
        this.permissionListener = listener
    }

    protected fun needRequestRuntimePermission(): Boolean
            = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    protected fun requestUnGranted(permission: Array<String>) {
        val unGrantedPermission: Array<String> = findIsPermissionUnGranted(permission)

        if (unGrantedPermission.isEmpty()) {
            permissionGranted()
            return
        }
        return ActivityCompat.requestPermissions(activity, permission, 0)
    }

    private fun findIsPermissionUnGranted(permissions: Array<String>): Array<String> {
        var list: MutableList<String> = ArrayList()
        for (permission in permissions) {
            if (!isPermissionGranted(permission))
                list.add(permission)
        }
        return list.toTypedArray()
    }

    private fun isPermissionGranted(permission: String): Boolean
            = ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED

    protected fun requestPermissionResult(permissions: Array<String>, grantResult: IntArray) {
        if (grantResult.size > 0) {
            for (gResult in grantResult) {
                if (gResult != PackageManager.PERMISSION_GRANTED) {
                    permissionDenied()
                    return
                }
            }
        }
        permissionGranted()
    }

    protected fun permissionGranted() { permissionListener.permissionGranted() }
    protected fun permissionDenied() { permissionListener.permissionDenied() }
}