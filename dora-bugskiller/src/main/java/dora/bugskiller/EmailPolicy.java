package dora.bugskiller;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class EmailPolicy extends CrashReportPolicyWrapper {

    private String mEmail;   //接收者的Email
    private String mTitle;   //接收者的Email标题
    private String mEmailServerUrl = "http://47.75.216.2:8080/requestCrashEmail";

    public EmailPolicy(CrashReportPolicy policy, String email, String title) {
        this(new DefaultGroup(), policy, email, title);
    }

    public EmailPolicy(CrashReportGroup group, CrashReportPolicy policy, String email, String title) {
        super(group, policy);
        this.mEmail = email;
        this.mTitle = title;
    }

    public void setEmailServerUrl(String url) {
        this.mEmailServerUrl = url;
    }

    @Override
    public void report(CrashInfo info, CrashReportGroup group) {
        super.report(info, group);
        OkHttpClient client = new OkHttpClient();
        HashMap<String, String> params = new HashMap<>();
        params.put("crash_info", info.toString());
        params.put("email", mEmail);
        params.put("title", mTitle);
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(mEmailServerUrl)
                .post(body)
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
