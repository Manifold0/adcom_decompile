// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface BaseApi
{
    void onPause(final Activity p0);
    
    void onResume(final Activity p0);
    
    void setAge(final int p0);
    
    void setGender(final String p0);
    
    void setMediationSegment(final String p0);
}
