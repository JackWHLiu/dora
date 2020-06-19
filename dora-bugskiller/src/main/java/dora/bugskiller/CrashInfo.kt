package dora.bugskiller

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

/**
 * 开发者可以扩展此类来自定义崩溃信息，这样的话，必须重写toString()方法生效。
 */
class CrashInfo(val context: Context) {
    //版本名称
    var versionName: String? = null

    //版本号
    var versionCode = 0

    //SDK版本号
    val sdkVersion: Int

    //Android版本号
    val release: String

    //手机型号
    val model: String

    //手机制造商
    val brand: String

    //崩溃线程
    var thread: Thread? = null

    //崩溃异常信息
    var throwable: Throwable? = null
        private set

    fun setException(e: Throwable?) {
        throwable = e
    }

    override fun toString(): String {
        return """
            Crash线程：${thread!!.name}#${thread!!.id}
            手机型号：$model
            手机品牌：$brand
            SDK版本：$sdkVersion
            Android版本：$release
            版本名称：$versionName
            版本号：$versionCode
            异常信息：${throwable.toString()}${buildStackTrace(throwable!!.stackTrace)}
            """.trimIndent()
    }

    fun buildStackTrace(lines: Array<StackTraceElement>): String {
        val sb = StringBuilder()
        for (line in lines) {
            sb.append("\n").append("at ").append(line.className).append(".")
                .append(line.methodName)
                .append("(").append(line.fileName + ":" + line.lineNumber).append(")")
        }
        return sb.toString()
    }

    init {
        //获取手机的一些信息
        val pm = context.packageManager
        val pkgInfo: PackageInfo
        try {
            pkgInfo = pm.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)
            versionName = pkgInfo.versionName
            versionCode = pkgInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            versionName = "unknown"
            versionCode = -1
        }
        sdkVersion = Build.VERSION.SDK_INT
        release = Build.VERSION.RELEASE
        model = Build.MODEL
        brand = Build.MANUFACTURER
    }
}