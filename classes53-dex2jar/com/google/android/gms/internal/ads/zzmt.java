// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;

public final class zzmt extends zzls
{
    private final VideoController.VideoLifecycleCallbacks zzuy;
    
    public zzmt(final VideoController.VideoLifecycleCallbacks zzuy) {
        this.zzuy = zzuy;
    }
    
    public final void onVideoEnd() {
        this.zzuy.onVideoEnd();
    }
    
    public final void onVideoMute(final boolean b) {
        this.zzuy.onVideoMute(b);
    }
    
    public final void onVideoPause() {
        this.zzuy.onVideoPause();
    }
    
    public final void onVideoPlay() {
        this.zzuy.onVideoPlay();
    }
    
    public final void onVideoStart() {
        this.zzuy.onVideoStart();
    }
}
