package dora.bugskiller;

public abstract class LogReportPolicyWrapper extends PolicyWrapper<LogInfo, LogReportPolicy> {

    protected LogReportPolicyWrapper(Group group, LogReportPolicy policy) {
        super(group, policy);
    }
}
