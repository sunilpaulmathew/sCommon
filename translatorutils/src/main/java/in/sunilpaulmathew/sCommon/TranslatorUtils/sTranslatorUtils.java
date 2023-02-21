package in.sunilpaulmathew.sCommon.TranslatorUtils;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import in.sunilpaulmathew.sCommon.CommonUtils.sCommonUtils;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 16, 2021
 */
public class sTranslatorUtils {

    private final Activity mActivity;
    private final MaterialAlertDialogBuilder mDialogBuilder;
    private final String mAppName, mUrl;

    public sTranslatorUtils(String appName, String url, Activity activity) {
        this.mActivity = activity;
        this.mAppName = appName;
        this.mUrl = url;
        this.mDialogBuilder = new MaterialAlertDialogBuilder(activity);
    }

    @SuppressLint("StringFormatInvalid")
    public void show() {
        mDialogBuilder.setIcon(R.drawable.ic_translate);
        mDialogBuilder.setTitle(R.string.translations);
        mDialogBuilder.setMessage(R.string.translations_message);
        mDialogBuilder.setNeutralButton(mActivity.getString(R.string.cancel), (dialog, which) -> {
        });
        mDialogBuilder.setNegativeButton(mActivity.getString(R.string.translate, mAppName), (dialog, which) ->
                sCommonUtils.launchUrl(mUrl, mActivity));
        mDialogBuilder.setPositiveButton(mActivity.getString(R.string.translate, mActivity.getString(R.string.lib_name)), (dialog, which) ->
                sCommonUtils.launchUrl("https://poeditor.com/join/project?hash=9AiLut8Dmy", mActivity)).show();
    }

}