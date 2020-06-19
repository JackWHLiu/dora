package dora.bugskiller;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * I make it out of okhttp network framework.
 * 我以网络框架okhttp为原材料制作了它。
 */
public class WebPolicy extends WebPolicyBase {

    public WebPolicy(String url, HashMap<String, String> params) {
        super(url, params);
    }

    public WebPolicy(String url, HashMap<String, String> params, CrashReportPolicy policy) {
        super(url, params, policy);
    }

    public WebPolicy(String url, HashMap<String, String> params, CrashReportGroup group) {
        super(url, params, group);
    }

    public WebPolicy(String url, HashMap<String, String> params, CrashReportGroup group, CrashReportPolicy policy) {
        super(url, params, group, policy);
    }

    @Override
    public void sendCrashInfoToWeb(String url, HashMap<String, String> params, CrashInfo info, CrashReportGroup group) {
        if (group.counts()) {
            OkHttpClient client = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("dora", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                }
            });
        }
    }
}
