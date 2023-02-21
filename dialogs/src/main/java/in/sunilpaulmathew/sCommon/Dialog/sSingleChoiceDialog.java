package in.sunilpaulmathew.sCommon.Dialog;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public abstract class sSingleChoiceDialog {

    private final int mIcon;
    private final int mPosition;
    private final MaterialAlertDialogBuilder mDialogBuilder;
    private final String mTitle;
    private final String[] mSingleChoiceItems;

    public sSingleChoiceDialog(int icon, String title, String[] singleChoiceItems,
                               int position, Context context) {
        this.mIcon = icon;
        this.mTitle = title;
        this.mSingleChoiceItems = singleChoiceItems;
        this.mPosition = position;
        this.mDialogBuilder = new MaterialAlertDialogBuilder(context);
    }

    private void startDialog() {
        if (mIcon > Integer.MIN_VALUE) {
            mDialogBuilder.setIcon(mIcon);
        }
        if (mTitle != null) {
            mDialogBuilder.setTitle(mTitle);
        }
        mDialogBuilder.setSingleChoiceItems(mSingleChoiceItems, mPosition, (dialog, itemPosition) -> {
            onItemSelected(itemPosition);
            dialog.dismiss();
        }).show();
    }

    public void show() {
        startDialog();
    }

    public abstract void onItemSelected(int position);
}