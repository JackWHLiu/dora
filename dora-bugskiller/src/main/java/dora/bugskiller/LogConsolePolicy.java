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

import android.app.Activity;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class LogConsolePolicy extends LogReportPolicy {

    private Activity mActivity;

    public LogConsolePolicy(Activity activity) {
        this(activity, new DefaultGroup());
    }

    public LogConsolePolicy(Activity activity, Group group) {
        super(group, null);
        this.mActivity = activity;
    }

    public LogConsolePolicy(Activity activity, LogReportPolicy policy) {
        this(activity, new DefaultGroup(), policy);
    }

    public LogConsolePolicy(Activity activity, Group group, LogReportPolicy policy) {
        super(group, policy);
        this.mActivity = activity;
    }

    @Override
    public void report(LogInfo info, Group group) {
        super.report(info, group);
        if (group.counts()) {
            if (info.getContent() == null || info.getContent().equals("")) {
                return;
            }
            if (mActivity != null) {
                Snackbar.make(mActivity.getWindow().getDecorView(), info.getContent(), Snackbar.LENGTH_INDEFINITE)
                        .setAction("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
            }
        }
    }
}