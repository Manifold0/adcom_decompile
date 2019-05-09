// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import android.view.View;

public interface VungleNativeAd
{
    void finishDisplayingAd();
    
    View renderNativeView();
    
    void setAdVisibility(final boolean p0);
}
