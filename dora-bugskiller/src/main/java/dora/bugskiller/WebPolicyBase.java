package dora.bugskiller;

public abstract class WebPolicyBase extends CrashReportPolicy {

    private String mRequestUrl;

    protected WebPolicyBase(String url) {
        this(url, new DefaultGroup());
    }

    protected WebPolicyBase(String url, CrashReportPolicy policy) {
        this(url, new DefaultGroup(), policy);
    }

    protected WebPolicyBase(String url, Group group) {
        super(group, null);
        this.mRequestUrl = url;
    }

    protected WebPolicyBase(String url, Group group, CrashReportPolicy policy) {
        super(group, policy);
        this.mRequestUrl = url;
    }

    @Override
    public void report(CrashInfo info, Group group) {
        super.report(info, group);
        sendCrashInfoToWeb(mRequestUrl, info, group);
    }

    /**
     * Sends crash log information to the remote server.
     * 把崩溃日志信息发送到远端服务器。
     */
    public abstract void sendCrashInfoToWeb(String url, CrashInfo info, Group group);
}
