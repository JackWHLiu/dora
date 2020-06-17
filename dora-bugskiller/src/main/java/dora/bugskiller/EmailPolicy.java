package dora.bugskiller;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.util.HashMap;

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
        if (group.counts()) {
            // The usage is similar to DoraWebPolicy.
            // 用法和DoraWebPolicy相似。
            OkHttpClient client = new OkHttpClient();
            HashMap<String, String> params = new HashMap<>();
            params.put("crash_info", info.toString());
            params.put("email", mEmail);
            params.put("title", mTitle);
            FormEncodingBuilder builder = new FormEncodingBuilder();
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
}
