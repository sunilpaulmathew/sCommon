package in.sunilpaulmathew.sCommon.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.util.List;

import in.sunilpaulmathew.sCommon.Activities.sCreditsActivity;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 17, 2021
 */
public class sCreditsUtils {

    private static Drawable mIcon, mBackButton;
    private static int mAccentColor, mTitleSize;
    private static List<sSerializableItems> mData;
    private static String mAppName, mCopyRight, mVersionName;

    public sCreditsUtils(List<sSerializableItems> data, Drawable icon, Drawable backButton, int color, int size,
                            String appName, String copyRight, String versionName) {
        mData = data;
        mIcon = icon;
        mBackButton = backButton;
        mAccentColor = color;
        mTitleSize = size;
        mAppName = appName;
        mCopyRight = copyRight;
        mVersionName = versionName;
    }

    public static Drawable getIcon() {
        return mIcon;
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

    public static List<sSerializableItems> getData() {
        return mData;
    }

    public static String getAppname() {
        return mAppName;
    }

    public static String getCopyRight() {
        return mCopyRight;
    }

    public static String geVersionName() {
        return mVersionName;
    }

    public void launchCredits(Context context) {
        Intent credits = new Intent(context, sCreditsActivity.class);
        context.startActivity(credits);
    }

}