package dora.bugskiller;

import android.content.Context;

public class CrashConfig {

    CrashReportPolicy policy;   //崩溃日志上报策略，提供本地存储、邮件接收、网页查看等内置策略，也可自定义
    CrashInfo info;     //包含崩溃信息内容
    boolean enabled;    //是否开启崩溃日志收集功能，不开启则testOnly功能无效
    boolean interceptCrash; //收集崩溃信息后，是否让应用闪退，true则不闪退

    public CrashConfig(Builder builder) {
        policy = builder.policy;
        info = builder.info;
        enabled = builder.enabled;
        interceptCrash = builder.interceptCrash;
    }

    public static class Builder {

        CrashReportPolicy policy = new StoragePolicy();
        CrashInfo info;
        boolean enabled = true;
        boolean interceptCrash;
        Context context;

        public Builder(Context context) {
            this.context = context;
            this.info = new CrashInfo(context);
        }

        public Builder crashReportPolicy(CrashReportPolicy policy) {
            this.policy = policy;
            return this;
        }

        public Builder crashInfo(CrashInfo info) {
            this.info = info;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder interceptCrash(boolean interceptCrash) {
            this.interceptCrash = interceptCrash;
            return this;
        }

        public CrashConfig build() {
            CrashConfig config = new CrashConfig(this);
            if (context != null) {
                DoraUncaughtExceptionHandler.getInstance().init(context, config);
            }
            return config;
        }
    }
}
