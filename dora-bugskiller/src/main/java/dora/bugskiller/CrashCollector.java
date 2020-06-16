package dora.bugskiller;

public class CrashCollector extends Collector {

    CrashInfo mInfo;

    @Override
    public void collect(CrashInfo info) {
        mInfo = info;
    }

    @Override
    public void reportCrash(Thread thread, CrashReportPolicy policy) {
        if (mInfo != null) {
            mInfo.setThread(thread);
            policy.report(mInfo, policy.getGroup());
        }
    }
}
