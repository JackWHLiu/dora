package dora.bugskiller;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class LogNotificationPolicy extends LogReportPolicyWrapper {

    private Context mContext;

    public LogNotificationPolicy(Context context) {
        this(context, new DefaultGroup());
    }

    public LogNotificationPolicy(Context context, Group group) {
        super(group, null);
        this.mContext = context;
    }

    public LogNotificationPolicy(Context context, LogReportPolicy policy) {
        this(context, new DefaultGroup(), policy);
    }

    public LogNotificationPolicy(Context context, Group group, LogReportPolicy policy) {
        super(group, policy);
        this.mContext = context;
    }

    @Override
    public void report(LogInfo info, Group group) {
        super.report(info, group);
        if (group.counts()) {
            if (info.getContent() == null || info.getContent().equals("")) {
                return;
            }
            if (mContext != null) {
                PendingIntent pi = PendingIntent.getActivity(mContext, 0x00, new Intent(), 0);
                NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(mContext)
                        .setContentTitle("测试标题")
                        .setContentText("测试内容")
                        .setContentIntent(pi)
                        .setTicker("测试通知来啦")
                        .setWhen(System.currentTimeMillis())
                        .setPriority(Notification.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .build();
                manager.notify(0x01, notification);
            }
        }
    }
}