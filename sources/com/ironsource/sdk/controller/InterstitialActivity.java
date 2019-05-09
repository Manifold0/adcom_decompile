package com.ironsource.sdk.controller;

import android.os.Bundle;
import com.ironsource.sdk.utils.Logger;

public class InterstitialActivity extends ControllerActivity {
    private static final String TAG = ControllerActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.m1212i(TAG, "onCreate");
    }

    protected void onResume() {
        super.onResume();
        Logger.m1212i(TAG, "onResume");
    }

    protected void onPause() {
        super.onPause();
        Logger.m1212i(TAG, "onPause");
    }
}
