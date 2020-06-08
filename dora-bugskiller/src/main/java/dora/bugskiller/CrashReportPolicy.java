package dora.bugskiller;

public interface CrashReportPolicy {

    void report(CrashInfo info);
}