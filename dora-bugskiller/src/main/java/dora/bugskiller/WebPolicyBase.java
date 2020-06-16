package dora.bugskiller;

public abstract class WebPolicyBase extends CrashReportPolicyWrapper {

    String mReportUrl;

    protected WebPolicyBase(CrashReportPolicy policy, String url) {
        this(new DefaultGroup(), policy, url);
    }

    protected WebPolicyBase(CrashReportGroup group, CrashReportPolicy policy, String url) {
        super(group, policy);
        this.mReportUrl = url;
    }

    protected WebPolicyBase(String url) {
        this(new DefaultGroup(), url);
    }

    protected WebPolicyBase(CrashReportGroup group, String url) {
        super(group, null);
        this.mReportUrl = url;
    }

    @Override
    public void report(CrashInfo info, CrashReportGroup group) {
        super.report(info, group);
        sendCrashInfoToWeb(mReportUrl, info, group);
    }

    /**
     * 把崩溃日志信息发送到远端服务器。
     *
     * @param url 服务器接收崩溃日志端点
     * @param info 崩溃的日志信息
     */
    public abstract void sendCrashInfoToWeb(String url, CrashInfo info, CrashReportGroup group);
}
