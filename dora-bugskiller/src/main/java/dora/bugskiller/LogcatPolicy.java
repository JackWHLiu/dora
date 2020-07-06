package dora.bugskiller;

import android.util.Log;

/**
 * This class is used only for test apis and is rarely used in a real production environment.
 * 此类仅作测试API使用，很少用于实际的生产环境。
 */
public class LogcatPolicy extends CrashReportPolicy {

    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_ERROR = 2;
    private int mLevel = LOG_LEVEL_DEBUG;

    public LogcatPolicy() {
        this(new DefaultGroup());
    }

    public LogcatPolicy(int level) {
        this(level, new DefaultGroup());
    }

    public LogcatPolicy(Group group) {
        super(group, null);
    }

    public LogcatPolicy(CrashReportPolicy policy) {
        this(new DefaultGroup(), policy);
    }

    public LogcatPolicy(int level, Group group) {
        super(group, null);
        this.mLevel = level;
    }

    public LogcatPolicy(int level, CrashReportPolicy policy) {
        this(level, new DefaultGroup(), policy);
    }

    public LogcatPolicy(Group group, CrashReportPolicy policy) {
        super(group, policy);
    }

    public LogcatPolicy(int level, Group group, CrashReportPolicy policy) {
        super(group, policy);
        this.mLevel = level;
    }

    @Override
    public void report(final CrashInfo info, Group group) {
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
