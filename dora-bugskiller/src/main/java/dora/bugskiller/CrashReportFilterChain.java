package dora.bugskiller;

import java.util.LinkedList;
import java.util.List;

public class CrashReportFilterChain {

    private List<CrashReportFilter> mFilters;

    public CrashReportFilterChain() {
        mFilters = new LinkedList<CrashReportFilter>();
    }

    public CrashReportFilterChain addFirst(CrashReportFilter filter) {
        mFilters.add(0, filter);
        return this;
    }

    public CrashReportFilterChain addLast(CrashReportFilter filter) {
        mFilters.add(filter);
        return this;
    }

    public CrashReportFilter getFilter() {
        CrashReportFilter first = null;
        CrashReportFilter last = null; //上次的
        for (int i = 0; i < mFilters.size(); i++) {
            CrashReportFilter filter = mFilters.get(i);
            if (i == 0) {
                first = filter;
            } else {
                last.setNextFilter(filter);
            }
            last = filter;
        }
        return first;
    }
}
