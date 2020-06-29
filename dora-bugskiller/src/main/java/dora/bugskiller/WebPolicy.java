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
 * I make it out of okhttp3 network framework.
 * 我以网络框架okhttp3为原材料制作了它。
 */
public class WebPolicy extends WebPolicyBase {

    private HashMap<String, String> mRequestParams;

    public WebPolicy(String url, HashMap<String, String> params) {
        super(url);
        this.mRequestParams = params;
    }

    public WebPolicy(String url, HashMap<String, String> params, CrashReportPolicy policy) {
        super(url, policy);
        this.mRequestParams = params;
    }

    public WebPolicy(String url, HashMap<String, String> params, Group group) {
        super(url, group);
        this.mRequestParams = params;
    }

    public WebPolicy(String url, HashMap<String, String> params, Group group, CrashReportPolicy policy) {
        super(url, group, policy);
        this.mRequestParams = params;
    }

    @Override
    public void sendCrashInfoToWeb(String url, CrashInfo info, Group group) {
        if (group.counts()) {
            OkHttpClient client = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : mRequestParams.keySet()) {
                builder.add(key, mRequestParams.get(key));
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
