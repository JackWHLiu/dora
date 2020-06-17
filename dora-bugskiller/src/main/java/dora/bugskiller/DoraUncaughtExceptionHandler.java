package dora.bugskiller;

import android.content.Context;
import android.os.Process;

class DoraUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private CrashConfig mConfig;

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;//系统的默认异常处理类

    private static DoraUncaughtExceptionHandler sInstance
            = new DoraUncaughtExceptionHandler();//用户自定义的异常处理类

    private DoraUncaughtExceptionHandler() {
    }

    static DoraUncaughtExceptionHandler getInstance() {
        return sInstance;
    }

    void init(Context context, CrashConfig config) {
        this.mContext = context.getApplicationContext();
        this.mConfig = config;
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (mConfig.enabled) {
            interceptException(t, e);
        }
        if (!mConfig.interceptCrash) {
            //如果系统提供了异常处理类，则交给系统去处理
            if (mDefaultExceptionHandler != null) {
                mDefaultExceptionHandler.uncaughtException(t, e);
            } else {
                //否则我们自己处理，自己处理通常是让app退出
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        }
    }

    public void interceptException(Thread t, Throwable e) {
        //收集异常信息，做我们自己的处理
        Collector collector = new CrashCollector();
        CrashInfo info = mConfig.info;
        info.setException(e);
        collector.collect(info);
        collector.reportCrash(t, mConfig.policy);
    }
}