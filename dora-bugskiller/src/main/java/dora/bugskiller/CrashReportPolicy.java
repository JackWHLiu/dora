package dora.bugskiller;

/**
 * 请继承它的实现类CrashReportPolicyWrapper。
 */
interface CrashReportPolicy {

    void report(CrashInfo info, CrashReportGroup group);

    CrashReportGroup getGroup();
}