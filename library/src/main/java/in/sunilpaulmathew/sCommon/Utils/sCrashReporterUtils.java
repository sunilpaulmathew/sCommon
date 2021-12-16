package in.sunilpaulmathew.sCommon.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import in.sunilpaulmathew.sCommon.Activities.sCrashReporterActivity;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on December 15, 2021
 * Ref: https://stackoverflow.com/questions/601503/how-do-i-obtain-crash-data-from-my-android-application
 */
public class sCrashReporterUtils implements Thread.UncaughtExceptionHandler {

    private static Drawable mBackButton;
    private static File mPath;
    private static int mAccentColor, mTitleSize;
    private final Thread.UncaughtExceptionHandler mDefaultUEH;

    public sCrashReporterUtils(Drawable backButton, File path, int color, int size) {
        mBackButton = backButton;
        mPath = path;
        mAccentColor = color;
        mTitleSize = size;
        this.mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static Drawable getBackButton() {
        return mBackButton;
    }

    public static int getAccentColor() {
        return mAccentColor;
    }

    public static int getTextSize() {
        return mTitleSize;
    }

    public static String getCrashLog() {
        return sUtils.read(mPath);
    }

    public void uncaughtException(@NonNull Thread t, Throwable e) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        String stacktrace = result.toString();
        printWriter.close();

        if (mPath != null) {
            sUtils.create(stacktrace, mPath);
        }

        mDefaultUEH.uncaughtException(t, e);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void initialize(Context context) {
        if (sUtils.exist(mPath)) {
            Intent crashLog = new Intent(context, sCrashReporterActivity.class);
            context.startActivity(crashLog);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new sCrashReporterUtils(mBackButton, mPath, mAccentColor, mTitleSize));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static void reload() {
        sUtils.delete(mPath);
        Thread.setDefaultUncaughtExceptionHandler(new sCrashReporterUtils(mBackButton, mPath, mAccentColor, mTitleSize));
    }

}