package in.sunilpaulmathew.sCommon.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import in.sunilpaulmathew.sCommon.Adapters.sCreditsAdapter;
import in.sunilpaulmathew.sCommon.R;
import in.sunilpaulmathew.sCommon.Utils.sCreditsUtils;

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
        mTitle.setTextColor(sCreditsUtils.getAccentColor());
        mAppName.setText(sCreditsUtils.getAppname());
        mAppName.setTextColor(sCreditsUtils.getAccentColor());
        mAppIcon.setImageDrawable(sCreditsUtils.getIcon());
        mBackButton.setImageDrawable(sCreditsUtils.getBackButton());
        mVersion.setText(getString(R.string.version, sCreditsUtils.geVersionName()));
        mCopyright.setText(getString(R.string.copyright, sCreditsUtils.getCopyRight()));

        mBackButton.setOnClickListener(v -> finish());
    }

}