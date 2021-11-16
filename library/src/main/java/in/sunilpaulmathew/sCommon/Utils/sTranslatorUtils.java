package in.sunilpaulmathew.sCommon.Utils;

import android.app.Activity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import in.sunilpaulmathew.sCommon.R;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 16, 2021
 */
public class sTranslatorUtils {

    public static MaterialAlertDialogBuilder showTranslationMessage(String appName, String url, Activity activity) {
        return new MaterialAlertDialogBuilder(activity)
                .setIcon(R.drawable.ic_translate)
                .setTitle(R.string.translations)
                .setMessage(R.string.translations_message)
                .setNeutralButton(activity.getString(R.string.cancel), (dialog, which) ->{
                })
                .setNegativeButton(activity.getString(R.string.translate, appName), (dialog, which) ->
                        sUtils.launchUrl(url, activity))

                .setPositiveButton(activity.getString(R.string.translate, activity.getString(R.string.lib_name)), (dialog, which) ->
                        sUtils.launchUrl("https://poeditor.com/join/project?hash=9AiLut8Dmy", activity));
    }

}