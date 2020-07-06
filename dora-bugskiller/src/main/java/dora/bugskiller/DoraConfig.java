package dora.bugskiller;

import android.content.Context;

public class DoraConfig {

    CrashReportPolicy policy;   //崩溃信息上报策略，提供本地存储、邮件接收、网页查看等内置策略，也可自定义
    CrashReportFilter filter;   //决定是否要上报该次崩溃信息
    CrashInfo info;     //包含崩溃信息内容
    boolean enabled;    //是否开启崩溃日志收集功能
    boolean interceptCrash; //收集崩溃信息后，是否让应用闪退，true则不闪退
    boolean initLogNotification;    //是否初始化日志通知服务

    public DoraConfig(Builder builder) {
        policy = builder.policy;
        filter = builder.filter;
        info = builder.info;
        enabled = builder.enabled;
        interceptCrash = builder.interceptCrash;
        initLogNotification = builder.initLogNotification;
    }

    public static class Builder {

        CrashReportPolicy policy = new StoragePolicy();
        CrashReportFilter filter = new DefaultFilter();
        CrashInfo info;
        boolean enabled = true;
        boolean interceptCrash;
        boolean initLogNotification;
        Context context;

        public Builder(Context context) {
            this.context = context;
            this.info = new CrashInfo(context);
        }

        public Builder crashReportPolicy(CrashReportPolicy policy) {
            this.policy = policy;
            return this;
        }

        // Either you create a CrashReportFilter directly, or you use the method named
        // "CrashReportFilterChain#getFilter()".
        // 要么你直接创建一个CrashReportFilter，要么你使用CrashReportFilterChain的getFilter()方法。
        public Builder filterChain(CrashReportFilter filter) {
            this.filter = filter;
            return this;
        }

        public Builder crashInfo(CrashInfo info) {
            this.info = info;
            return this;
        }

        // Determines if you want to activate all functions of this framework.
        // 确定你是否想要激活属于这个框架的所有功能。
        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder interceptCrash(boolean interceptCrash) {
            this.interceptCrash = interceptCrash;
            return this;
        }

        public Builder initLogNotification(boolean initLogNotification) {
            this.initLogNotification = initLogNotification;
            return this;
        }

        public DoraConfig build() {
            DoraConfig config = new DoraConfig(this);
            if (context != null) {
                DoraUncaughtExceptionHandler.getInstance().init(context, config);
            }
            return config;
        }
    }
}
