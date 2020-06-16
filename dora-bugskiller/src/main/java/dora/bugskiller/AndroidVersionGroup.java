package dora.bugskiller;

import android.os.Build;

public class AndroidVersionGroup implements CrashReportGroup {

    private int num;

    public AndroidVersionGroup(int num) {
        this.num = num;
    }

    @Override
    public boolean counts() {
        return Build.VERSION.RELEASE.equals(num);
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }
}
