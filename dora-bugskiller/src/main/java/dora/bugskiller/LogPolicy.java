package dora.bugskiller;

import android.util.Log;

/**
 * 此类仅作测试API使用，很少用于实际的生产环境。
 */
public class LogPolicy extends CrashReportPolicyWrapper {

    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_ERROR = 2;
    private int mLevel = LOG_LEVEL_DEBUG;

    public LogPolicy(CrashReportPolicy policy) {
        this(new DefaultGroup(), policy);
    }

    public LogPolicy(CrashReportGroup group, CrashReportPolicy policy) {
        super(group, policy);
    }

    public LogPolicy() {
        this(new DefaultGroup());
    }

    public LogPolicy(CrashReportGroup group) {
        super(group, null);
    }

    public LogPolicy(CrashReportPolicy policy, int level) {
        this(new DefaultGroup(), policy, level);
    }

    public LogPolicy(CrashReportGroup group, CrashReportPolicy policy, int level) {
        super(group, policy);
        this.mLevel = level;
    }

    public LogPolicy(int level) {
        this(new DefaultGroup(), level);
    }

    public LogPolicy(CrashReportGroup group, int level) {
        super(group, null);
        this.mLevel = level;
    }

    @Override
    public void report(final CrashInfo info, CrashReportGroup group) {
        super.report(info, group);
        if (group.counts()) {
            if (mLevel == LOG_LEVEL_DEBUG) {
                Log.d("dora", info.toString());
            }
            if (mLevel == LOG_LEVEL_INFO) {
                Log.i("dora", info.toString());
            }
            if (mLevel == LOG_LEVEL_ERROR) {
                Log.e("dora", info.toString());
            }
        }
    }
}
