package dora.bugskiller;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.util.HashMap;

/**
 * I make it out of okhttp network framework.
 * 我以网络框架okhttp为原材料制作了它。
 */
public class DoraWebPolicy extends WebPolicyBase {

    public DoraWebPolicy(String url) {
        super(url);
    }

    public DoraWebPolicy(String url, CrashReportPolicy policy) {
        super(url, policy);
    }

    public DoraWebPolicy(String url, CrashReportGroup group) {
        super(url, group);
    }

    public DoraWebPolicy(String url, CrashReportGroup group, CrashReportPolicy policy) {
        super(url, group, policy);
    }

    @Override
    public void sendCrashInfoToWeb(String url, CrashInfo info, CrashReportGroup group) {
        if (group.counts()) {
            OkHttpClient client = new OkHttpClient();
            HashMap<String, String> params = new HashMap<>();
            params.put("versionName", info.getVersionName());
            params.put("versionCode", String.valueOf(info.getVersionCode()));
            params.put("sdkVersion", String.valueOf(info.getSdkVersion()));
            params.put("androidVersion", info.getRelease());
            params.put("model", info.getModel());
            params.put("brand", info.getBrand());
            params.put("exception", info.toString());
            FormEncodingBuilder builder = new FormEncodingBuilder();
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder()
                    .url(url)
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
