// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class NativeExpressAdView extends BaseAdView
{
    public NativeExpressAdView(final Context context) {
        super(context, 1);
    }
    
    public NativeExpressAdView(final Context context, final AttributeSet set) {
        super(context, set, 1);
    }
    
    public NativeExpressAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, 1);
    }
    
    public final VideoController getVideoController() {
        return this.zzut.getVideoController();
    }
    
    public final VideoOptions getVideoOptions() {
        return this.zzut.getVideoOptions();
    }
    
    public final void setVideoOptions(final VideoOptions videoOptions) {
        this.zzut.setVideoOptions(videoOptions);
    }
}
