package dora.bugskiller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

public class DoraNotificationService extends Service {

    private IBinder mBinder;
    private RemoteViews mRemoteViews;
    private NotificationManager mNotificationManager;
    private PendingIntent mPendingIntent;
    private static final int NOTIFICATION_ID = 0x01;

    @Override
    public IBinder onBind(Intent intent) {
        mBinder = new DoraNotificationServiceImpl();
        return mBinder;
    }

    private class DoraNotificationServiceImpl extends INotificationService.Stub {

        @Override
        public void updateNotification(String title, String content) throws RemoteException {
            DoraNotificationService.this.updateNotification(title, content);
        }

        @Override
        public void cancelNotification() throws RemoteException {
            DoraNotificationService.this.cancelNotification();
        }
    }

    private void updateNotification(String title, String content) {
        String channelId = "dora.bugskiller";
        String channelName = "Dora";
        NotificationChannel channel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(false);
            channel.setSound(null, null);
            channel.setShowBadge(false);
            mNotificationManager.createNotificationChannel(channel);
        }
        Notification notification;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notification = new NotificationCompat.Builder(this).build();
        } else {
            notification = new Notification.Builder(this).setChannelId(channelId).build();
        }
        notification.icon = R.mipmap.dora_logo;
        notification.tickerText = title;
        notification.contentIntent = mPendingIntent;
        notification.contentView = mRemoteViews;
        notification.flags = Notification.FLAG_NO_CLEAR;
        mRemoteViews.setTextViewText(R.id.tv_dora_title, title);
        mRemoteViews.setTextViewText(R.id.tv_dora_content, content);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void cancelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            stopForeground(true);
        }
        mNotificationManager.cancel(NOTIFICATION_ID);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.dora_notification);
    }
}
