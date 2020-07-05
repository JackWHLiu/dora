package dora.bugskiller;

public abstract class CrashReportPolicy extends PolicyWrapper<CrashInfo, CrashReportPolicy> {

    protected CrashReportPolicy(Group group, CrashReportPolicy policy) {
        super(group, policy);
    }
}