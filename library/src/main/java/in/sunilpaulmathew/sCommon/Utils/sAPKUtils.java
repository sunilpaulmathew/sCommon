package in.sunilpaulmathew.sCommon.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.Objects;

import in.sunilpaulmathew.sCommon.R;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public class sAPKUtils {

    public static CharSequence getAPKName(String apkPath, Context context) {
        if (getPackageInfo(apkPath, context) != null) {
            return Objects.requireNonNull(getPackageInfo(apkPath, context)).applicationInfo.loadLabel(getPackageManager(context));
        } else {
            return null;
        }
    }

    public static Drawable getAPKIcon(String apkPath, Context context) {
        if (getPackageInfo(apkPath, context) != null) {
            return Objects.requireNonNull(getPackageInfo(apkPath, context)).applicationInfo.loadIcon(getPackageManager(context));
        } else {
            return sUtils.getDrawable(R.drawable.ic_android, context);
        }
    }

    public static int getVersionCode(String apkPath, Context context) {
        return Objects.requireNonNull(getPackageInfo(apkPath, context)).versionCode;
    }

    private static PackageInfo getPackageInfo(String apkPath, Context context) {
        try {
            return getPackageManager(context).getPackageArchiveInfo(apkPath, 0);
        } catch (Exception ignored) {
        }
        return null;
    }

    private static PackageManager getPackageManager(Context context) {
        return context.getPackageManager();
    }

    public static String getAPKSize(String path) {
        long size = new File(path).length() / 1024;
        long decimal = (size - 1024) / 1024;
        if (size > 1024) {
            return size / 1024 + "." + decimal + " MB";
        } else {
            return size  + " KB";
        }
    }

    private static String getPackageName(String apkPath, Context context) {
        if (getPackageInfo(apkPath, context) != null) {
            return Objects.requireNonNull(getPackageInfo(apkPath, context)).packageName;
        } else {
            return null;
        }
    }

    public static String getVersionName(String apkPath, Context context) {
        if (getPackageInfo(apkPath, context) != null) {
            return Objects.requireNonNull(getPackageInfo(apkPath, context)).versionName;
        } else {
            return null;
        }
    }

}