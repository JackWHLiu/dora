package dora.bugskiller;

public abstract class LogReportPolicy extends PolicyWrapper<LogInfo, LogReportPolicy> {

    protected LogReportPolicy(Group group, LogReportPolicy policy) {
        super(group, policy);
    }
}
