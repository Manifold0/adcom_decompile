// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.util.Log;
import android.os.Bundle;
import android.app.Activity;

public class UnityPlayerProxyActivity extends Activity
{
    protected void onCreate(final Bundle bundle) {
        Log.w("Unity", "UnityPlayerProxyActivity has been deprecated, please update your AndroidManifest to use UnityPlayerActivity instead");
        super.onCreate(bundle);
    }
}
