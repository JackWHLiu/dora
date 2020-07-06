package dora.bugskiller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class DoraNotificationManager extends INotificationService.Stub {

    private Context mContext;
    private INotificationService mService;
    private ServiceConnection mNotificationConnection;

    public DoraNotificationManager(Context context) {
        this.mContext = context;
        this.mNotificationConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = asInterface(service);
                DoraLog.debug("DoraNotificationManager connected.");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                DoraLog.debug("DoraNotificationManager disconnected.");
            }
        };
    }

    public void connectService() {
        Intent intent = new Intent(DoraConstants.ACTION_NOTIFICATION_SERVICE);
        intent.setClass(mContext, DoraNotificationService.class);
        mContext.bindService(intent, mNotificationConnection, Context.BIND_AUTO_CREATE);
    }

    public void disconnectService() {
        mContext.unbindService(mNotificationConnection);
        mContext.stopService(new Intent(DoraConstants.ACTION_NOTIFICATION_SERVICE));
    }

    @Override
    public void updateNotification(String title, String content) {
        try {
            mService.updateNotification(title, content);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelNotification() {
        try {
            mService.cancelNotification();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
