package in.sunilpaulmathew.sCommon.Utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import in.sunilpaulmathew.sCommon.R;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public class sUtils {

    public static boolean delete(File file) {
        if (file.isDirectory()) {
            for (File files : Objects.requireNonNull(file.listFiles()))
                delete(files);
        }
        return file.delete();
    }

    public static boolean exist(File file) {
        return file.exists();
    }

    public static boolean getBoolean(String name, boolean defaults, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(name, defaults);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static boolean isDarkTheme(Context context) {
        int currentNightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    public static boolean mkdir(File folderPath) {
        return folderPath.mkdirs();
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

    public static Intent filePickerIntent(boolean multipleFiles, int requestCode,
                                          String fileType, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(fileType != null ? fileType : "*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, multipleFiles);
        }
        activity.startActivityForResult(intent, requestCode);
        return intent;
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

    public static String getLanguage(Context context) {
        return getString("appLanguage", java.util.Locale.getDefault().getLanguage(),
                context);
    }

    public static String getString(String name, String defaults, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(name, defaults);
    }

    public static String read(File file) {
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new FileReader(file));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = buf.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString().trim();
        } catch (IOException ignored) {
        } finally {
            try {
                if (buf != null) buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String read(Uri uri, Context context) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            for (int result = bis.read(); result != -1; result = bis.read()) {
                buf.write((byte) result);
            }
            return buf.toString("UTF-8");
        } catch (IOException ignored) {
        }
        return null;
    }

    public static String readAssetFile(String assetName, Context context) {
        InputStream input = null;
        BufferedReader buf = null;
        try {
            StringBuilder s = new StringBuilder();
            input = context.getAssets().open(assetName);
            buf = new BufferedReader(new InputStreamReader(input));

            String str;
            while ((str = buf.readLine()) != null) {
                s.append(str).append("\n");
            }
            return s.toString().trim();
        } catch (IOException ignored) {
        } finally {
            try {
                if (input != null) input.close();
                if (buf != null) buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Toast toast(String message, Context context) {
        return Toast.makeText(context, message, Toast.LENGTH_LONG);
    }

    public static void create(String text, File path) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();
        } catch (IOException ignored) {
        }
    }

    // Make sure permission to write to the destination available
    public static void copy(File source, File dest) {
        try {
            FileInputStream inputStream = new FileInputStream(source);
            FileOutputStream outputStream = new FileOutputStream(dest);

            copyStream(inputStream, outputStream);

            inputStream.close();
            outputStream.close();
        } catch (IOException ignored) {}
    }

    // // Requires android.permission.WRITE_EXTERNAL_STORAGE
    public static void copy(Uri uri, File dest, Context context) {
        try (FileOutputStream outputStream = new FileOutputStream(dest, false)) {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            
            copyStream(inputStream, outputStream);
            
            inputStream.close();
        } catch (IOException ignored) {}
    }

    public static void copyAssetFile(String assetName, File dest, Context context) {
        try {
            InputStream inputStream = context.getAssets().open(assetName);
            FileOutputStream outputStream = new FileOutputStream(dest);

            copyStream(inputStream, outputStream);

            inputStream.close();
            outputStream.close();
        } catch (IOException ignored) {}
    }

    public static void copyDir(File sourceDir, File destDir) {
        if (!exist(destDir)) {
            mkdir(destDir);
        }
        for (File mFile : Objects.requireNonNull(sourceDir.listFiles())) {
            if (mFile.isDirectory()) {
                copyDir(mFile, new File(destDir, mFile.getName()));
            } else {
                copy(mFile, new File(destDir, mFile.getName()));
            }
        }
    }

    public static void copyStream(InputStream from, OutputStream to) throws IOException {
        byte[] buf = new byte[1024 * 1024];
        int len;
        while ((len = from.read(buf)) > 0) {
            to.write(buf, 0, len);
        }
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

    // Should be called before setContentView of launcher activity
    public static void setLanguage(Context context) {
        Locale myLocale = new Locale(getString("appLanguage", java.util.Locale.getDefault()
                .getLanguage(), context));
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException ignored) {}
    }

}