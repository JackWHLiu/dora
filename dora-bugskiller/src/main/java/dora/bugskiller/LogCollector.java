package dora.bugskiller;

public class LogCollector extends Collector<LogInfo, LogReportPolicy> {

    private LogInfo mInfo;

    @Override
    public void collect(LogInfo info) {
        this.mInfo = info;
    }

    @Override
    public void report(LogReportPolicy policy) {
        if (mInfo != null) {
            policy.report(mInfo, policy.getGroup());
        }
    }
}
