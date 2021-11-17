package in.sunilpaulmathew.sCommon.Utils;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 17, 2021
 */
public class sSerializableItems implements Serializable {

    private final Drawable mIcon;
    private final String mTextOne, mTextTwo, mTextThree;

    public sSerializableItems(Drawable icon, String textOne, String texTwo, String textThree) {
        this.mIcon = icon;
        this.mTextOne = textOne;
        this.mTextTwo = texTwo;
        this.mTextThree = textThree;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getTextOne() {
        return mTextOne;
    }

    public String getTextTwo() {
        return mTextTwo;
    }

    public String getTextThree() {
        return mTextThree;
    }

}