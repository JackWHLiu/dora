package dora.bugskiller;

public abstract class CrashReportPolicyWrapper extends PolicyWrapper<CrashInfo, CrashReportPolicy> {

    protected CrashReportPolicyWrapper(Group group, CrashReportPolicy policy) {
        super(group, policy);
    }
}
