package dora.bugskiller;

import android.os.Build;

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
