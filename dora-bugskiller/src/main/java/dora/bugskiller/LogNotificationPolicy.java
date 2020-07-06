package dora.bugskiller;

import android.content.Context;

/**
 * Don't forget request notification permission.
 * 不要忘记申请通知栏权限。
 */
public class LogNotificationPolicy extends LogReportPolicy {

    private Context mContext;
    private DoraNotificationManager mNotificationManager;

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

    public DoraNotificationManager getNotificationManager() {
        return mNotificationManager;
    }

    @Override
    public void report(LogInfo info, Group group) {
        super.report(info, group);
        if (group.counts()) {
            if (info.getContent() == null || info.getContent().equals("")) {
                return;
            }
            if (mContext != null) {
                mNotificationManager = new DoraNotificationManager(mContext);
                mNotificationManager.connectService();
                mNotificationManager.updateNotification(info.getTag(), info.getContent());
            }
        }
    }
}