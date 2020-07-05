package dora.bugskiller;

public abstract class CrashReportPolicy implements Policy<CrashInfo> {

    protected Group mGroup;
    protected CrashReportPolicy mBasePolicy;

    protected CrashReportPolicy(Group group, CrashReportPolicy policy) {
        this.mGroup = group;
        this.mBasePolicy = policy;
    }

    @Override
    public Group getGroup() {
        return mGroup;
    }

    @Override
    public void report(CrashInfo info, Group group) {
        if (mBasePolicy != null) {
            mBasePolicy.report(info, mBasePolicy.getGroup());
        }
    }
}