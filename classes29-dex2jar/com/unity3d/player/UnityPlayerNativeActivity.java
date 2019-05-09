// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.util.Log;
import android.os.Bundle;

public class UnityPlayerNativeActivity extends UnityPlayerActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        Log.w("Unity", "UnityPlayerNativeActivity has been deprecated, please update your AndroidManifest to use UnityPlayerActivity instead");
        super.onCreate(bundle);
    }
}
