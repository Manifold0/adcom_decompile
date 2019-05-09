// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import android.os.Bundle;
import android.view.View;

public interface IAdUnitViewHandler
{
    boolean create(final AdUnitActivity p0);
    
    boolean destroy();
    
    View getView();
    
    void onCreate(final AdUnitActivity p0, final Bundle p1);
    
    void onDestroy(final AdUnitActivity p0);
    
    void onPause(final AdUnitActivity p0);
    
    void onResume(final AdUnitActivity p0);
    
    void onStart(final AdUnitActivity p0);
    
    void onStop(final AdUnitActivity p0);
}
