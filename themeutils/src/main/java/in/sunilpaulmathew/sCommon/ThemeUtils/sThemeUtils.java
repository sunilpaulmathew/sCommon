package in.sunilpaulmathew.sCommon.ThemeUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

import in.sunilpaulmathew.sCommon.CommonUtils.sCommonUtils;
import in.sunilpaulmathew.sCommon.Dialog.sSingleChoiceDialog;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on February 20, 2023
 */
public class sThemeUtils {

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static boolean isDarkTheme(Context context) {
        int currentNightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    private static int getAppThemePosition(Context context) {
        return sCommonUtils.getInt("appTheme", 0, context);
    }

    public static String getAppTheme(Context context) {
        int appTheme = sCommonUtils.getInt("appTheme", 0, context);
        switch (appTheme) {
            case 2:
                return context.getString(R.string.app_theme_light);
            case 1:
                return context.getString(R.string.app_theme_dark);
            default:
                return context.getString(R.string.app_theme_auto);
        }
    }

    public static String getLanguage(Context context) {
        return sCommonUtils.getString("appLanguage", java.util.Locale.getDefault().getLanguage(),
                context);
    }

    private static String[] getAppThemeMenu(Context context) {
        return new String[] {
                context.getString(R.string.app_theme_auto),
                context.getString(R.string.app_theme_dark),
                context.getString(R.string.app_theme_light)
        };
    }

    /*
     * Values: 0 - Auto; 1 - Dark; 2 - Light
     * Should be called before setContentView of launcher activity
     */
    public static void initializeAppTheme(Context context) {
        int appTheme = sCommonUtils.getInt("appTheme", 0, context);
        switch (appTheme) {
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
        }
    }

    public static void setAppTheme(boolean exit, Activity activity) {
        new sSingleChoiceDialog(R.drawable.ic_theme, activity.getString(R.string.app_theme),
                getAppThemeMenu(activity), getAppThemePosition(activity), activity) {

            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onItemSelected(int position) {
                if (position == sCommonUtils.getInt("appTheme", 0, activity)) {
                    return;
                }
                switch (position) {
                    case 2:
                        sCommonUtils.saveInt("appTheme", 2, activity);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case 1:
                        sCommonUtils.saveInt("appTheme", 1, activity);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    default:
                        sCommonUtils.saveInt("appTheme", 0, activity);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                }
                if (exit) {
                    activity.finish();
                }
            }
        }.show();
    }

    public static void setAppTheme(Activity activity) {
        setAppTheme(false, activity);
    }

    /*
     * Should be called before setContentView of launcher activity
     */
    public static void setLanguage(Context context) {
        Locale myLocale = new Locale(sCommonUtils.getString("appLanguage", java.util.Locale.getDefault()
                .getLanguage(), context));
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

}