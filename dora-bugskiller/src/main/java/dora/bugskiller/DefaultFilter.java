package dora.bugskiller;

public class DefaultFilter extends CrashReportFilter {

    @Override
    public boolean handle(CrashInfo info) {
        //默认不作过滤处理
        return true;
    }
}
