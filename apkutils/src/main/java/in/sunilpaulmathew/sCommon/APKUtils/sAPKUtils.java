package in.sunilpaulmathew.sCommon.APKUtils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Locale;
import java.util.Objects;

import in.sunilpaulmathew.sCommon.CommonUtils.sCommonUtils;

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

    /*
     * The input color should be app theme based
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getAPKIcon(String apkPath, Context context) {
        if (getPackageInfo(apkPath, context) != null) {
            return Objects.requireNonNull(getPackageInfo(apkPath, context)).applicationInfo.loadIcon(getPackageManager(context));
        } else {
            return sCommonUtils.getDrawable(R.drawable.ic_android, context);
        }
    }

    public static int getVersionCode(String apkPath, Context context) {
        return Objects.requireNonNull(getPackageInfo(apkPath, context)).versionCode;
    }

    private static PackageInfo getPackageInfo(String apkPath, Context context) {
        try {
            PackageInfo pi = getPackageManager(context).getPackageArchiveInfo(apkPath, 0);

            if (pi != null) {
                ApplicationInfo ai = pi.applicationInfo;
                Objects.requireNonNull(ai).sourceDir = apkPath;
                ai.publicSourceDir = apkPath;
                return pi;
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private static PackageManager getPackageManager(Context context) {
        return context.getPackageManager();
    }

    public static String getAPKSize(long sizeInBytes) {
        if (sizeInBytes < 0) return "0 B";

        final String[] units = {"B", "KB", "MB", "GB", "TB"};
        double size = sizeInBytes;
        int unitIndex = 0;

        while (size >= 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }

        return String.format(Locale.getDefault(), "%.2f %s", size, units[unitIndex])
                .replaceAll("\\.?0+\\s", " ");
    }

    public static String getPackageName(String apkPath, Context context) {
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