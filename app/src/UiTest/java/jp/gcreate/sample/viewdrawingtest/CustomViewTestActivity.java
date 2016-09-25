package jp.gcreate.sample.viewdrawingtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

public class CustomViewTestActivity extends AppCompatActivity {
    @VisibleForTesting
    CustomView testTarget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_test_activity);
        testTarget = (CustomView) findViewById(R.id.sut);
    }

    @VisibleForTesting
    public void invalidateView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                testTarget.invalidate();
            }
        });
    }
}