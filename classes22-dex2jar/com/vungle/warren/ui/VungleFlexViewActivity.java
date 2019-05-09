// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.ui;

import android.util.Log;
import android.os.Build$VERSION;

public class VungleFlexViewActivity extends VungleActivity
{
    private static final String TAG;
    
    static {
        TAG = VungleFlexViewActivity.class.getSimpleName();
    }
    
    @Override
    protected boolean canRotate() {
        boolean b;
        if (this.getApplication().getApplicationInfo().targetSdkVersion >= 27 && Build$VERSION.SDK_INT == 26) {
            b = true;
        }
        else {
            b = false;
        }
        Log.d(VungleFlexViewActivity.TAG, "allow rotation = " + !b);
        return !b;
    }
}
