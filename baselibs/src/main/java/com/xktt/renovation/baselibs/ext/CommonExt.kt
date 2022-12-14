package com.xktt.renovation.baselibs.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.Checkable
import android.widget.TextView
import com.xktt.renovation.baselibs.R
import com.xktt.renovation.baselibs.config.AppConfig
import com.xktt.renovation.baselibs.utils.NLog
import com.xktt.renovation.baselibs.widget.CustomToast

/**
 * @author chenxz
 * @date 2018/11/20
 * @desc
 */

fun Any.loge(content: String?) {
    val tag = this.javaClass.simpleName ?: AppConfig.TAG
    NLog.e(tag, content ?: "")
}

fun dp2px(dpValue: Float): Int {
    return (0.5f + dpValue * Resources.getSystem().displayMetrics.density).toInt()
}

fun Fragment.showToast(content: String) {
    CustomToast(this.activity?.applicationContext, content).show()
}

fun Context.showToast(content: String) {
    CustomToast(this, content).show()
}

fun Activity.showSnackMsg(msg: String) {
    val snackbar = Snackbar.make(this.window.decorView, msg, Snackbar.LENGTH_SHORT)
    val view = snackbar.view
    view.findViewById<TextView>(R.id.snackbar_text)
        .setTextColor(ContextCompat.getColor(this, R.color.white))
    snackbar.show()
}

fun Fragment.showSnackMsg(msg: String) {
    this.activity ?: return
    val snackbar = Snackbar.make(this.requireActivity().window.decorView, msg, Snackbar.LENGTH_SHORT)
    val view = snackbar.view
    view.findViewById<TextView>(R.id.snackbar_text)
        .setTextColor(ContextCompat.getColor(this.requireActivity(), R.color.white))
    snackbar.show()
}

fun Context.openBrowser(url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).run { startActivity(this) }
}

// 扩展点击事件属性(重复点击时长)
var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0

// 重复点击事件绑定
inline fun <T : View> T.setSingleClickListener(time: Long = 1000, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}
