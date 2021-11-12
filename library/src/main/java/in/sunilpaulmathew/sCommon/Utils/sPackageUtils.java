package in.sunilpaulmathew.sCommon.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.text.DateFormat;
import java.util.Objects;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public class sPackageUtils {

    private static ApplicationInfo getApplicationInfo(String packageName, Context context) {
        try {
            return getPackageManager(context).getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean isEnabled(String packageName, Context context) {
        return Objects.requireNonNull(getApplicationInfo(packageName, context)).enabled;
    }

    public static boolean isPackageInstalled(String packageName, Context context) {
        try {
            getPackageManager(context).getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return false;
    }

    public static boolean isSystemApp(String packageName, Context context) {
        try {
            return (Objects.requireNonNull(getApplicationInfo(packageName, context)).flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (NullPointerException ignored) {}
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    public static boolean isUpdatedSystemApp(String packageName, Context context) {
        try {
            return (Objects.requireNonNull(getApplicationInfo(packageName, context)).flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0;
        } catch (NullPointerException ignored) {}
        return false;
    }

    public static CharSequence getAppName(String packageName, Context context) {
        return getPackageManager(context).getApplicationLabel(Objects.requireNonNull(getApplicationInfo(
                packageName, context)));
    }

    public static Drawable getAppIcon(String packageName, Context context) {
        return getPackageManager(context).getApplicationIcon(Objects.requireNonNull(getApplicationInfo(packageName, context)));
    }

    public static int getVersionCode(String packageName, Context context) {
        return Objects.requireNonNull(getPackageManager(context).getPackageArchiveInfo(getSourceDir(packageName, context), 0)).versionCode;
    }

    private static PackageInfo getPackageInfo(String packageName, Context context) {
        try {
            return getPackageManager(context).getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
        } catch (Exception ignored) {
        }
        return null;
    }

    private static PackageManager getPackageManager(Context context) {
        return context.getPackageManager();
    }

    public static String getDataDir(String packageName, Context context) {
        return Objects.requireNonNull(getApplicationInfo(packageName, context)).dataDir;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static String getInstalledDate(String packageName, Context context) {
        return DateFormat.getDateTimeInstance().format(Objects.requireNonNull(getPackageInfo(packageName, context)).firstInstallTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static String getNativeLibDir(String packageName, Context context) {
        return Objects.requireNonNull(getApplicationInfo(packageName, context)).nativeLibraryDir;
    }

    public static String getParentDir(String packageName, Context context) {
        return Objects.requireNonNull(new File(Objects.requireNonNull(getApplicationInfo(packageName, context))
                .sourceDir).getParentFile()).toString();
    }

    public static String getSourceDir(String packageName, Context context) {
        return Objects.requireNonNull(getApplicationInfo(packageName, context)).sourceDir;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static String getUpdatedDate(String packageName, Context context) {
        return DateFormat.getDateTimeInstance().format(Objects.requireNonNull(getPackageInfo(packageName, context)).lastUpdateTime);
    }

    public static String getVersionName(String packageName, Context context) {
        return Objects.requireNonNull(getPackageManager(context).getPackageArchiveInfo(getSourceDir(packageName, context), 0)).versionName;
    }

}