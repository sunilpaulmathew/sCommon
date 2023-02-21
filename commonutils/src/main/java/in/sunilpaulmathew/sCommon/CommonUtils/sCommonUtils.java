package in.sunilpaulmathew.sCommon.CommonUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on February 20, 2023
 */
public class sCommonUtils {

    public static boolean getBoolean(String name, boolean defaults, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(name, defaults);
    }

    public static Drawable getDrawable(int drawable, Context context) {
        return ContextCompat.getDrawable(context, drawable);
    }

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static int getColor(int color, Context context) {
        return ContextCompat.getColor(context, color);
    }

    public static int getInt(String name, int defaults, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(name, defaults);
    }

    public static int getOrientation(Activity activity) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && activity.isInMultiWindowMode() ?
                Configuration.ORIENTATION_PORTRAIT : activity.getResources().getConfiguration().orientation;
    }

    public static long getLong(String name, long defaults, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(name, defaults);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(String name, Set<String> defaults, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(name, defaults);
    }

    public static Snackbar snackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.dismiss, v -> snackbar.dismiss());
        return snackbar;
    }

    public static String getString(String name, String defaults, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(name, defaults);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HH-mm").format(new Date());
    }

    public static Toast toast(String message, Context context) {
        return Toast.makeText(context, message, Toast.LENGTH_LONG);
    }

    public static void launchUrl(String url, Activity activity) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            activity.startActivity(i);
        } catch (ActivityNotFoundException ignored) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void remove(String name, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(name).apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void saveBoolean(String name, boolean value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(name, value).apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void saveInt(String name, int value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(name, value).apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void saveLong(String name, long value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(name,value).apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void saveString(String name, String value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(name, value).apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void saveStringSet(String name, Set<String> value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(name, value).apply();
    }

    public static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException ignored) {}
    }

}