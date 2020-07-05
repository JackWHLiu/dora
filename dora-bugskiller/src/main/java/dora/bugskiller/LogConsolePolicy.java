package dora.bugskiller;

import android.app.Activity;

import com.google.android.material.snackbar.Snackbar;

public class LogConsolePolicy extends PolicyWrapper<LogInfo, LogReportPolicy> {

    private Activity mActivity;

    public LogConsolePolicy(Activity activity) {
        this(activity, new DefaultGroup());
    }

    public LogConsolePolicy(Activity activity, Group group) {
        super(group, null);
        this.mActivity = activity;
    }

    public LogConsolePolicy(Activity activity, LogReportPolicy policy) {
        this(activity, new DefaultGroup(), policy);
    }

    public LogConsolePolicy(Activity activity, Group group, LogReportPolicy policy) {
        super(group, policy);
        this.mActivity = activity;
    }

    @Override
    public void report(LogInfo info, Group group) {
        super.report(info, group);
        if (group.counts()) {
            if (info.getContent() == null || info.getContent().equals("")) {
                return;
            }
            if (mActivity != null) {
                Snackbar.make(mActivity.getWindow().getDecorView(), info.getContent(), Snackbar.LENGTH_LONG).show();
            }
        }
    }
}