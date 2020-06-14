package dora.bugskiller;

public abstract class WebPolicyBase extends CrashReportPolicyWrapper {

    String mReportUrl;

    protected WebPolicyBase(CrashReportPolicy policy, String url) {
        super(policy);
        this.mReportUrl = url;
    }

    protected WebPolicyBase(String url) {
        super(null);
        this.mReportUrl = url;
    }

    @Override
    public void report(CrashInfo info) {
        super.report(info);
        sendCrashInfoToWeb(mReportUrl, info);
    }

    /**
     * 把崩溃日志信息发送到远端服务器。
     *
     * @param url 服务器接收崩溃日志端点
     * @param info 崩溃的日志信息
     */
    public abstract void sendCrashInfoToWeb(String url, CrashInfo info);
}
