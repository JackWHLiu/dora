package dora.bugskiller;

import java.util.HashMap;

public abstract class WebPolicyBase extends CrashReportPolicyWrapper {

    private String mRequestUrl;
    private HashMap<String,String> mRequestParams;


    protected WebPolicyBase(String url, HashMap<String,String> params) {
        this(url, params, new DefaultGroup());
    }

    protected WebPolicyBase(String url, HashMap<String,String> params, CrashReportPolicy policy) {
        this(url, params, new DefaultGroup(), policy);
    }

    protected WebPolicyBase(String url, HashMap<String,String> params, CrashReportGroup group) {
        super(group, null);
        this.mRequestUrl = url;
        this.mRequestParams = params;
    }

    protected WebPolicyBase(String url, HashMap<String,String> params, CrashReportGroup group, CrashReportPolicy policy) {
        super(group, policy);
        this.mRequestUrl = url;
        this.mRequestParams = params;
    }

    @Override
    public void report(CrashInfo info, CrashReportGroup group) {
        super.report(info, group);
        sendCrashInfoToWeb(mRequestUrl, mRequestParams, info, group);
    }

    /**
     * 把崩溃日志信息发送到远端服务器。
     *
     * @param url 服务器接收崩溃日志端点
     * @param info 崩溃的日志信息
     */
    public abstract void sendCrashInfoToWeb(String url, HashMap<String,String> params, CrashInfo info, CrashReportGroup group);
}
