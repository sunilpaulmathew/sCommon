package in.sunilpaulmathew.common.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import in.sunilpaulmathew.sCommon.Utils.sSingleItemDialog;
import in.sunilpaulmathew.sCommon.Utils.sUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialCardView mDemoCard = findViewById(R.id.demo_card);
        mDemoCard.setOnClickListener(v ->
                new sSingleItemDialog(0,"Demo Items Menu", getMenu(),this) {

                    @Override
                    public void onItemSelected(int position) {
                        sUtils.toast(getMenu()[position] + " Selected", MainActivity.this).show();
                    }
                }.show()
        );
    }

    private String[] getMenu() {
        return new String[] {
                "Pick File", "Read File", "Delete File", "Copy File", "Make Folder", "Launch URL"
        };
    }

}