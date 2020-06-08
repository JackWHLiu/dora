package dora.bugskiller;

public abstract class Collector {

    /**
     * 收集崩溃信息。
     *
     * @param info
     */
    public abstract void collect(CrashInfo info);

    /**
     * 根据需要将收集到的崩溃信息反馈给开发者。
     *
     * @param thread 崩溃发生的线程
     * @param policy 崩溃报告的方针
     */
    public abstract void reportCrash(Thread thread, CrashReportPolicy policy);
}