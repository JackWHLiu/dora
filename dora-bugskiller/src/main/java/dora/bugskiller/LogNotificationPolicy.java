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
        this.mNotificationManager = DoraUncaughtExceptionHandler.getInstance().getNotificationManager();
    }

    public LogNotificationPolicy(Context context, LogReportPolicy policy) {
        this(context, new DefaultGroup(), policy);
    }

    public LogNotificationPolicy(Context context, Group group, LogReportPolicy policy) {
        super(group, policy);
        this.mContext = context;
        this.mNotificationManager = DoraUncaughtExceptionHandler.getInstance().getNotificationManager();
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
                if (mNotificationManager  != null) {
                    mNotificationManager.updateNotification(info.getTag(), info.getContent());
                } else {
                    throw new RuntimeException("Configure true for DoraConfig#initLogNotification()");
                }
            }
        }
    }
}