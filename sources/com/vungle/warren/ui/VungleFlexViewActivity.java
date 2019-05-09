package com.vungle.warren.ui;

import android.os.Build.VERSION;
import android.util.Log;

public class VungleFlexViewActivity extends VungleActivity {
    private static final String TAG = VungleFlexViewActivity.class.getSimpleName();

    protected boolean canRotate() {
        boolean blockRotate;
        boolean z;
        if (getApplication().getApplicationInfo().targetSdkVersion < 27 || VERSION.SDK_INT != 26) {
            blockRotate = false;
        } else {
            blockRotate = true;
        }
        String str = TAG;
        StringBuilder append = new StringBuilder().append("allow rotation = ");
        if (blockRotate) {
            z = false;
        } else {
            z = true;
        }
        Log.d(str, append.append(z).toString());
        if (blockRotate) {
            return false;
        }
        return true;
    }
}
