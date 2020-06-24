package dora.bugskiller;

import androidx.annotation.CallSuper;

/**
 * Wrap it so that various strategies can be combined freely, the innermost policy is invoked first,
 * and so on. To extend this class, override the report() method.
 * 包装一下，便于多种策略自由组合，最里面的策略最先被执行，以此类推，扩展此类请重写report()方法。
 */
public abstract class CrashReportPolicyWrapper implements CrashReportPolicy {

    private CrashReportGroup mCrashGroup;   //策略自己的分组
    private CrashReportPolicy mBasePolicy;

    protected CrashReportPolicyWrapper(CrashReportGroup group, CrashReportPolicy policy) {
        this.mCrashGroup = group;
        this.mBasePolicy = policy;
    }

    @Override
    public CrashReportGroup getGroup() {
        return mCrashGroup;
    }

    @CallSuper
    @Override
    public void report(CrashInfo info, CrashReportGroup group) {
        if (mBasePolicy != null) {
            mBasePolicy.report(info, mBasePolicy.getGroup());
        }
    }
}
