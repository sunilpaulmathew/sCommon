package in.sunilpaulmathew.sCommon.Utils;

import android.content.Context;
import android.content.Intent;
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

    private final Context mContext;
    private static int mAccentColor, mTitleSize;
    private final Thread.UncaughtExceptionHandler mDefaultUEH;

    public sCrashReporterUtils(int color, int size, Context context) {
        mAccentColor = color;
        mTitleSize = size;
        mContext = context;
        mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void uncaughtException(@NonNull Thread t, Throwable e) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        String stacktrace = result.toString();
        printWriter.close();

        sUtils.create(stacktrace, new File(mContext.getExternalFilesDir("logs"), "crashLog_" + sUtils.getTimeStamp()));
        sUtils.saveString("crashLog", sUtils.getTimeStamp(), mContext);
        sUtils.saveInt("accentColor", mAccentColor, mContext);
        sUtils.saveInt("titleSize", mTitleSize, mContext);

        Intent intent = new Intent(mContext, sCrashReporterActivity.class);
        intent.putExtra("crashLog", stacktrace);
        intent.putExtra("accentColor", mAccentColor);
        intent.putExtra("titleSize", mTitleSize);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);

        mDefaultUEH.uncaughtException(t, e);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void initialize() {
        String timeStamp = sUtils.getString("crashLog", null, mContext);
        if (timeStamp != null) {
            Intent intent = new Intent(mContext, sCrashReporterActivity.class);
            intent.putExtra("crashLog", sUtils.read(new File(mContext.getExternalFilesDir("logs"), "crashLog_" + timeStamp)));
            intent.putExtra("accentColor", sUtils.getInt("accentColor", Integer.MIN_VALUE, mContext));
            intent.putExtra("titleSize", sUtils.getInt("titleSize", Integer.MIN_VALUE, mContext));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(intent);
        }
        Thread.setDefaultUncaughtExceptionHandler(new sCrashReporterUtils(mAccentColor, mTitleSize, mContext));
    }

}