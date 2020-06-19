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

    protected DoraWebPolicy(String url) {
        super(url);
    }

    protected DoraWebPolicy(String url, CrashReportPolicy policy) {
        super(url, policy);
    }

    protected DoraWebPolicy(String url, CrashReportGroup group) {
        super(url, group);
    }

    protected DoraWebPolicy(String url, CrashReportGroup group, CrashReportPolicy policy) {
        super(url, group, policy);
    }

    @Override
    public void sendCrashInfoToWeb(String url, CrashInfo info, CrashReportGroup group) {
        if (group.counts()) {
            HashMap<String, String> params = new HashMap<>();
            params.put("versionName", info.getVersionName());
            params.put("versionCode", String.valueOf(info.getVersionCode()));
            params.put("sdkVersion", String.valueOf(info.getSdkVersion()));
            params.put("androidVersion", info.getRelease());
            params.put("model", info.getModel());
            params.put("brand", info.getBrand());
            params.put("androidException", info.toString());
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
