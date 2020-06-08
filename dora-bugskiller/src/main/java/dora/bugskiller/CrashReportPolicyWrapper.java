package dora.bugskiller;

import androidx.annotation.CallSuper;

/**
 * 包装一下，便于多种策略自由组合，最里面的最先被执行，以此类推，扩展此类请重写report()方法。
 */
public class CrashReportPolicyWrapper implements CrashReportPolicy {

    private CrashReportPolicy mBasePolicy;

    public CrashReportPolicyWrapper() {
    }

    public CrashReportPolicyWrapper(CrashReportPolicy policy) {
        this.mBasePolicy = policy;
    }

    @CallSuper
    @Override
    public void report(CrashInfo info) {
        if (mBasePolicy != null) {
            mBasePolicy.report(info);
        }
    }
}
