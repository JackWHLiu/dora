package dora.bugskiller;

/**
 * Example for custom filter, a main thread filter.
 * 自定义过滤器示范，主线程过滤器。
 */
public class ActivityThreadFilter extends CrashReportFilter {

    @Override
    public boolean handle(CrashInfo info) {
        String name = info.getThread().getName();
        // Only handle crash information for the main thread.
        // 只处理主线程的崩溃信息
        if (name.equals("main")) {
            return true;
        }
        return false;
    }
}
