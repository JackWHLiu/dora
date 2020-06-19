package dora.bugskiller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * 开发者可以扩展此类来自定义崩溃信息，这样的话，必须重写toString()方法生效。
 */
public class CrashInfo {

    //版本名称
    private String versionName;
    //版本号
    private int versionCode;
    //SDK版本号
    private int sdkVersion;
    //Android版本号
    private String release;
    //手机型号
    private String model;
    //手机制造商
    private String brand;
    //崩溃线程
    private Thread thread;
    //崩溃异常信息
    private Throwable throwable;
    private Context context;

    public CrashInfo(Context context) {
        this.context = context;
        //获取手机的一些信息
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo;
        try {
            pkgInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            versionName = pkgInfo.versionName;
            versionCode = pkgInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "unknown";
            versionCode = -1;
        }
        sdkVersion = Build.VERSION.SDK_INT;
        release = Build.VERSION.RELEASE;
        model = Build.MODEL;
        brand = Build.MANUFACTURER;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public int getSdkVersion() {
        return sdkVersion;
    }

    public String getRelease() {
        return release;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Thread getThread() {
        return thread;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public Context getContext() {
        return context;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setException(Throwable e) {
        this.throwable = e;
    }

    @Override
    public String toString() {
        return "\nCrash线程："+thread.getName()+"#"+thread.getId()
                + "\n手机型号：" + model
                + "\n手机品牌：" + brand
                + "\nSDK版本：" + sdkVersion
                + "\nAndroid版本：" + release
                + "\n版本名称：" + versionName
                + "\n版本号：" + versionCode
                + "\n异常信息：" + throwable.toString()
                + buildStackTrace(throwable.getStackTrace());
    }

    public String buildStackTrace(StackTraceElement[] lines) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement line : lines) {
            sb.append("\n").append("at ").append(line.getClassName()).append(".").append(line.getMethodName())
                    .append("(").append(line.getFileName()+":"+line.getLineNumber()).append(")");
        }
        return sb.toString();
    }
}