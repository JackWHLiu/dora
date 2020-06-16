package dora.bugskiller;

import android.os.Build;

public abstract class BrandGroup implements CrashReportGroup {

    public abstract String getBrandName();

    @Override
    public boolean counts() {
        return Build.BRAND.equals(getBrandName());
    }
}
