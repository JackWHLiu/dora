package dora.bugskiller;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class DoraWebPolicy extends WebPolicyBase {

    public DoraWebPolicy(CrashReportPolicy policy, String url) {
        super(policy, url);
    }

    public DoraWebPolicy(String url) {
        super(url);
    }

    @Override
    public void sendCrashInfoToWeb(String url, CrashInfo info) {
        OkHttpClient client = new OkHttpClient();
        HashMap<String,String> params = new HashMap<>();
        params.put("crash_info", info.toString());
        FormBody.Builder builder = new FormBody.Builder();
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