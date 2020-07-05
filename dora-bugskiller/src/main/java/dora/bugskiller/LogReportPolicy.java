package dora.bugskiller;

public abstract class LogReportPolicy implements Policy<LogInfo> {

    protected Group mGroup;
    protected LogReportPolicy mBasePolicy;

    protected LogReportPolicy(Group group, LogReportPolicy policy) {
        this.mGroup = group;
        this.mBasePolicy = policy;
    }

    @Override
    public Group getGroup() {
        return mGroup;
    }

    @Override
    public void report(LogInfo info, Group group) {
        if (mBasePolicy != null) {
            mBasePolicy.report(info, mBasePolicy.getGroup());
        }
    }
}
