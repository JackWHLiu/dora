package dora.bugskiller;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.util.HashMap;

/**
 * Huh!I make it out of okhttp network framework.
 * 哈！我以网络框架okhttp为原材料制作了它。
 */
public class DoraWebPolicy extends WebPolicyBase {

    public DoraWebPolicy(CrashReportPolicy policy, String url) {
        super(policy, url);
    }

    public DoraWebPolicy(CrashReportGroup group, CrashReportPolicy policy, String url) {
        super(group, policy, url);
    }

    public DoraWebPolicy(String url) {
        super(url);
    }

    public DoraWebPolicy(CrashReportGroup group, String url) {
        super(group, url);
    }

    @Override
    public void sendCrashInfoToWeb(String url, CrashInfo info, CrashReportGroup group) {
        OkHttpClient client = new OkHttpClient();
        HashMap<String,String> params = new HashMap<>();
        params.put("crash_info", info.toString());
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
