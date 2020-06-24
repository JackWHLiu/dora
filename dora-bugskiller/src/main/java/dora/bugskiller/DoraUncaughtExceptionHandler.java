package dora.bugskiller;

import android.content.Context;
import android.os.Process;

class DoraUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private CrashConfig mConfig;

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private static DoraUncaughtExceptionHandler sInstance
            = new DoraUncaughtExceptionHandler();

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
            boolean filterResult = mConfig.filter.filterCrashInfo(mConfig.info);
            if (filterResult) {
                interceptException(t, e);
            }
        }
        if (!mConfig.interceptCrash) {
            // If the Android system provides an exception handling class, it shall have handled
            // by the system.
            // 如果系统提供了异常处理类，则交给系统去处理
            if (mDefaultExceptionHandler != null) {
                mDefaultExceptionHandler.uncaughtException(t, e);
            } else {
                // Otherwise we handle it ourselves, we handle it ourselves usually by letting
                // the app exit
                // 否则我们自己处理，自己处理通常是让app退出
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        }
    }

    public void interceptException(Thread t, Throwable e) {
        // Collect exception information and do our own handling
        // 收集异常信息，做我们自己的处理
        Collector collector = new CrashCollector();
        CrashInfo info = mConfig.info;
        info.setThrowable(e);
        collector.collect(info);
        collector.reportCrash(t, mConfig.policy);
    }
}