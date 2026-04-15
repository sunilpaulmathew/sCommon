package in.sunilpaulmathew.sCommon.CrashReporter;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import in.sunilpaulmathew.sCommon.APKUtils.sAPKUtils;
import in.sunilpaulmathew.sCommon.CommonUtils.sCommonUtils;
import in.sunilpaulmathew.sCommon.PackageUtils.sPackageUtils;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on December 15, 2021
 */
public class sCrashReporterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_reporter);

        AppCompatEditText mCrashSteps = findViewById(R.id.crash_steps);
        AppCompatImageButton mBackButton = findViewById(R.id.back);
        MaterialButton mCancelButton = findViewById(R.id.cancel_button);
        MaterialButton mReportButton = findViewById(R.id.report_button);
        MaterialTextView mTitle = findViewById(R.id.title);
        MaterialTextView mCrashLog = findViewById(R.id.crash_log);

        View root = findViewById(R.id.layout_root);

        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            view.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        int mAccentColor = getIntent().getIntExtra("accentColor", Integer.MIN_VALUE);
        int mTitleSize = getIntent().getIntExtra("titleSize", 20);

        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleSize);

        if (mAccentColor != Integer.MIN_VALUE) {
            mTitle.setTextColor(mAccentColor);
            mCancelButton.setBackgroundColor(mAccentColor);
            mCrashLog.setTextColor(mAccentColor);
            mCrashSteps.setTextColor(mAccentColor);
            mCrashSteps.requestFocus();
            mReportButton.setBackgroundColor(mAccentColor);
            mBackButton.setColorFilter(mAccentColor);
        }

        if (getIntent().getStringExtra("crashLog") != null) {
            mCrashLog.setText(getIntent().getStringExtra("crashLog"));
        }

        mBackButton.setOnClickListener(v -> exit());
        mCancelButton.setOnClickListener(v -> exit());

        mReportButton.setOnClickListener(v -> {
            String mSteps = "";
            if (mCrashSteps.getText() != null && !mCrashSteps.getText().toString().trim().isEmpty()) {
                mSteps = mCrashSteps.getText().toString();
            }
            Intent share_log = new Intent();
            share_log.setAction(Intent.ACTION_SEND);
            share_log.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crash_report) + "/" +
                    sPackageUtils.getAppName(getPackageName(), this));
            share_log.putExtra(Intent.EXTRA_TEXT, "App Name: " + sPackageUtils.getAppName(
                    getPackageName(), this) + "\nPackage Name: " + getPackageName() + "\nApp Version: " +
                    sAPKUtils.getVersionName(sPackageUtils.getSourceDir(getPackageName(), this), this) +
                    "\nSDK Version: " + Build.VERSION.SDK_INT + "\n\nCrash Report\n\n" + mCrashLog.getText() +
                    "\n\nSteps to reproduce the issue: " + mSteps);
            share_log.setType("text/plain");
            Intent shareIntent = Intent.createChooser(share_log, "Share");
            startActivity(shareIntent);
        });

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                exit();
            }
        });
    }

    private void exit() {
        if (sCommonUtils.getString("crashLog", null, this) != null) {
            sCommonUtils.saveString("crashLog", null, this);
        }
        if (sCommonUtils.getInt("accentColor", Integer.MIN_VALUE, this) != Integer.MIN_VALUE) {
            sCommonUtils.saveInt("accentColor", Integer.MIN_VALUE, this);
        }
        if (sCommonUtils.getInt("titleSize", Integer.MIN_VALUE, this) != Integer.MIN_VALUE) {
            sCommonUtils.saveInt("titleSize", Integer.MIN_VALUE, this);
        }
        finish();
    }

}