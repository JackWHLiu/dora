package dora.bugskiller;

import android.content.Context;

public class CrashConfig {

    CrashReportPolicy policy;   //崩溃信息上报策略，提供本地存储、邮件接收、网页查看等内置策略，也可自定义
    CrashReportFilter filter;   //决定是否要上报该次崩溃信息
    CrashInfo info;     //包含崩溃信息内容
    boolean testOnly;   //true则只在debug包收集崩溃日志，false在debug和release包收集崩溃日志
    boolean enabled;    //是否开启崩溃日志收集功能，不开启则testOnly功能无效
    boolean interceptCrash; //收集崩溃信息后，是否让应用闪退，true则不闪退

    public CrashConfig(Builder builder) {
        policy = builder.policy;
        filter = builder.filter;
        info = builder.info;
        testOnly = builder.testOnly;
        enabled = builder.enabled;
        interceptCrash = builder.interceptCrash;
    }

    public static class Builder {

        CrashReportPolicy policy = new StoragePolicy();
        CrashReportFilter filter = new DefaultFilter();
        CrashInfo info;
        boolean enabled = true;
        boolean testOnly = true;
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

        public Builder filterChain(CrashReportFilter filter) {
            this.filter = filter;
            return this;
        }

        public Builder crashInfo(CrashInfo info) {
            this.info = info;
            return this;
        }

        public Builder testOnly(boolean testOnly) {
            this.testOnly = testOnly;
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

        public CrashConfig build() {
            CrashConfig config = new CrashConfig(this);
            if (context != null) {
                DoraUncaughtExceptionHandler.getInstance().init(context, config);
            }
            return config;
        }
    }
}
