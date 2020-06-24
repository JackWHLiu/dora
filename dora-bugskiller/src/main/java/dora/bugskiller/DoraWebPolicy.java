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
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("versionName", info.getVersionName());
            params.put("versionCode", String.valueOf(info.getVersionCode()));
            params.put("sdkVersion", String.valueOf(info.getSdkVersion()));
            params.put("androidVersion", info.getRelease());
            params.put("model", info.getModel());
            params.put("brand", info.getBrand());
            params.put("androidException", info.getThrowable().getMessage() + info.getException());
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
