package dora.bugskiller;

public class CrashCollector extends Collector<CrashInfo, CrashReportPolicy> {

    private CrashInfo mInfo;

    @Override
    public void collect(CrashInfo info) {
        this.mInfo = info;
    }

    @Override
    public void report(CrashReportPolicy policy) {
        reportCrash(policy, policy.getGroup());
    }

    private void reportCrash(CrashReportPolicy policy, Group group) {
        policy.report(mInfo, group);
    }
}
