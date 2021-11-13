package in.sunilpaulmathew.sCommon.Utils;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import in.sunilpaulmathew.sCommon.R;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 13, 2021
 */
public class sThemeUtils {

    private static int getAppThemePosition(Context context) {
        return sUtils.getInt("appTheme", 0, context);
    }

    public static String getAppTheme(Context context) {
        int appTheme = sUtils.getInt("appTheme", 0, context);
        switch (appTheme) {
            case 2:
                return context.getString(R.string.app_theme_light);
            case 1:
                return context.getString(R.string.app_theme_dark);
            default:
                return context.getString(R.string.app_theme_auto);
        }
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
        int appTheme = sUtils.getInt("appTheme", 0, context);
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

    public static void setAppTheme(Context context) {
        new sSingleChoiceDialog(R.drawable.ic_theme, context.getString(R.string.app_theme),
                getAppThemeMenu(context), getAppThemePosition(context), context) {

            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case 2:
                        sUtils.saveInt("appTheme", 2, context);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case 1:
                        sUtils.saveInt("appTheme", 1, context);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    default:
                        sUtils.saveInt("appTheme", 0, context);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                }
            }
        }.show();
    }

}