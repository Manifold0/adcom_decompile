// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.app.Activity;

public class GoogleARCoreApi
{
    public final native void initializeARCore(final Activity p0);
    
    public final native void pauseARCore();
    
    public final native void resumeARCore();
}
