package dora.bugskiller;

import java.util.Calendar;

public class TimeFilter extends CrashReportFilter {

    @Override
    public boolean handle(CrashInfo info) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);   //24小时制
        //只处理上班时间产生的日志
        if (hour >= 8 && hour < 20) {
            return true;
        }
        return false;
    }
}
