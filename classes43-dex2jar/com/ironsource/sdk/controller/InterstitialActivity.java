// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import com.ironsource.sdk.utils.Logger;
import android.os.Bundle;

public class InterstitialActivity extends ControllerActivity
{
    private static final String TAG;
    
    static {
        TAG = ControllerActivity.class.getSimpleName();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        Logger.i(InterstitialActivity.TAG, "onCreate");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Logger.i(InterstitialActivity.TAG, "onPause");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Logger.i(InterstitialActivity.TAG, "onResume");
    }
}
