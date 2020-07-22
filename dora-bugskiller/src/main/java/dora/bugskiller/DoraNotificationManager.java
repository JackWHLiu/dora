/*
 * Copyright (C) 2020 The Dora Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dora.bugskiller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * It is used to write log information to the notification bar.
 * 它被用来写入日志信息到通知栏。
 */
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
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
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
