package in.sunilpaulmathew.sCommon.Utils;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public abstract class sSingleItemDialog {

    private final int mIcon;
    private final MaterialAlertDialogBuilder mDialogBuilder;
    private final String mTitle;
    private final String[] mSingleChoiceItems;

    public sSingleItemDialog(int icon, String title, String[] singleChoiceItems, Context context) {
        this.mIcon = icon;
        this.mTitle = title;
        this.mSingleChoiceItems = singleChoiceItems;
        this.mDialogBuilder = new MaterialAlertDialogBuilder(context);
    }

    private void startDialog() {
        if (mIcon > Integer.MIN_VALUE) {
            mDialogBuilder.setIcon(mIcon);
        }
        if (mTitle != null) {
            mDialogBuilder.setTitle(mTitle);
        }
        mDialogBuilder.setItems(mSingleChoiceItems, (dialog, itemPosition) -> {
            onItemSelected(itemPosition);
            dialog.dismiss();
        }).show();
    }

    public void show() {
        startDialog();
    }

    public abstract void onItemSelected(int position);
}