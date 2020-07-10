package dora.bugskiller;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFilePolicy extends LogReportPolicy {

    private String mFolderName = "android-dora"; //手机系统根目录保存日志文件夹的名称

    public LogFilePolicy() {
        this(new DefaultGroup());
    }

    public LogFilePolicy(String folderName) {
        this(folderName, new DefaultGroup());
    }

    public LogFilePolicy(Group group) {
        super(group, null);
    }

    public LogFilePolicy(LogReportPolicy policy) {
        this(new DefaultGroup(), policy);
    }

    public LogFilePolicy(String folderName, Group group) {
        super(group, null);
        this.mFolderName = folderName;
    }

    public LogFilePolicy(String folderName, LogReportPolicy policy) {
        this(folderName, new DefaultGroup(), policy);
    }

    public LogFilePolicy(Group group, LogReportPolicy policy) {
        super(group, policy);
    }

    public LogFilePolicy(String folderName, Group group, LogReportPolicy policy) {
        super(group, policy);
        this.mFolderName = folderName;
    }

    @Override
    public void report(LogInfo info, Group group) {
        super.report(info, group);
        try {
            if (group.counts()) {
                if (info.getContent() == null || info.getContent().equals("")) {
                    return;
                }
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                String time = simpleDateFormat.format(new Date());
                File folder = new File(path, mFolderName);
                folder.mkdirs();
                File file = new File(folder.getAbsolutePath(), "log" + time + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                byte[] buffer = info.getContent().trim().getBytes();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(buffer, 0, buffer.length);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            Log.e("dora", "日志信息存储失败");
        }
    }
}