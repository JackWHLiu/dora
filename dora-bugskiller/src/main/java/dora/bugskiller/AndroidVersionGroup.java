package dora.bugskiller;

import android.os.Build;

/**
 * The group of Android mobile phone system version, for example, Android 6.0.
 * 安卓手机版本分组，例如Android6.0。
 */
public class AndroidVersionGroup implements CrashReportGroup {

    private String version;

    public AndroidVersionGroup(String version) {
        this.version = version;
    }

    @Override
    public boolean counts() {
        return Build.VERSION.RELEASE.equals(version);
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }
}
