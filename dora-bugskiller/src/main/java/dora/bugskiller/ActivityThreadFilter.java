package dora.bugskiller;

public class ActivityThreadFilter extends CrashReportFilter {

    @Override
    public boolean handle(CrashInfo info) {
        String name = info.getThread().getName();
        //只处理主线程的崩溃信息
        if (name.equals("main")) {
            return true;
        }
        return false;
    }
}
