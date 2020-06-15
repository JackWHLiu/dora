package dora.bugskiller;

public abstract class CrashReportFilter {

    private CrashReportFilter mNext;

    public CrashReportFilter setNextFilter(CrashReportFilter filter) {
        this.mNext = filter;
        return nextFilter();
    }

    public CrashReportFilter nextFilter() {
        if (mNext != null) {
            return mNext;
        }
        //没有下一环了就返回自己
        return this;
    }

    boolean filterCrashInfo(CrashInfo info) {
        boolean result = handle(info);
        if (result && mNext != null) {
            return mNext.handle(info);
        } else {
            return result;
        }
    }

    public abstract boolean handle(CrashInfo info);
}
