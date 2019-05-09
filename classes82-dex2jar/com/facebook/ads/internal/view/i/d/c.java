// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.d;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.i.a.a;

public interface c
{
    void a();
    
    void a(final int p0);
    
    void a(final a p0);
    
    void a(final boolean p0);
    
    void b();
    
    void c();
    
    boolean d();
    
    void e();
    
    int getCurrentPosition();
    
    int getDuration();
    
    long getInitialBufferTime();
    
    a getStartReason();
    
    d getState();
    
    int getVideoHeight();
    
    int getVideoWidth();
    
    View getView();
    
    float getVolume();
    
    void setBackgroundPlaybackEnabled(final boolean p0);
    
    void setControlsAnchorView(final View p0);
    
    void setFullScreen(final boolean p0);
    
    void setRequestedVolume(final float p0);
    
    void setVideoMPD(@Nullable final String p0);
    
    void setVideoStateChangeListener(final e p0);
    
    void setup(final Uri p0);
}
