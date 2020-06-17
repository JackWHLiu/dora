package dora.bugskiller;

/**
 * At the beginning, affects whether the crash info will report.
 * 在一开始的时候，影响崩溃信息是否会上报。
 */
public abstract class CrashReportFilter {

    private CrashReportFilter mNext;

    public void setNextFilter(CrashReportFilter filter) {
        this.mNext = filter;
    }

    public CrashReportFilter nextFilter() {
        return mNext;
    }

    boolean filterCrashInfo(CrashInfo info) {
        boolean result = handle(info);
        if (result && mNext != null) {
            return mNext.filterCrashInfo(info);
        } else {
            return result;
        }
    }

    public abstract boolean handle(CrashInfo info);
}
