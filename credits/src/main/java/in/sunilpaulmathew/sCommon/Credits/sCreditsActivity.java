package in.sunilpaulmathew.sCommon.Credits;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 17, 2021
 */
public class sCreditsActivity extends AppCompatActivity {

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        AppCompatImageButton mBackButton = findViewById(R.id.back);
        AppCompatImageView mAppIcon = findViewById(R.id.icon);
        MaterialTextView mAppName = findViewById(R.id.name);
        MaterialTextView mTitle = findViewById(R.id.title);
        MaterialTextView mVersion = findViewById(R.id.version);
        MaterialTextView mCopyright = findViewById(R.id.copyright);
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        sCreditsAdapter mRecycleViewAdapter = new sCreditsAdapter(sCreditsUtils.getData(), sCreditsUtils.getAccentColor());
        mRecyclerView.setAdapter(mRecycleViewAdapter);

        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, sCreditsUtils.getTextSize());
        if (sCreditsUtils.getAccentColor() != Integer.MIN_VALUE) {
            mTitle.setTextColor(sCreditsUtils.getAccentColor());
            mAppName.setTextColor(sCreditsUtils.getAccentColor());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                mBackButton.setColorFilter(sCreditsUtils.getAccentColor());
            }
        }
        mAppName.setText(sCreditsUtils.getAppname());
        mAppIcon.setImageDrawable(sCreditsUtils.getIcon());
        mBackButton.setImageDrawable(sCreditsUtils.getBackButton());
        mVersion.setText(getString(R.string.version, sCreditsUtils.geVersionName()));
        mCopyright.setText(getString(R.string.copyright, sCreditsUtils.getCopyRight()));

        mBackButton.setOnClickListener(v -> finish());
    }

}