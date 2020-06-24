package dora.bugskiller;

import java.util.Calendar;

public class TimeFilter extends CrashReportFilter {

    @Override
    public boolean handle(CrashInfo info) {
        // 24-hour
        // 24小时制
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        // Only process logs during office hours
        // 只处理上班时间的日志
        if (hour >= 8 && hour < 20) {
            return true;
        }
        return false;
    }
}
