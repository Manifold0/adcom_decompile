// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.internal.ads.zzane;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;

public final class NativeContentAdView extends NativeAdView
{
    public NativeContentAdView(final Context context) {
        super(context);
    }
    
    public NativeContentAdView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public NativeContentAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public NativeContentAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    public final View getAdvertiserView() {
        return super.zzp("1004");
    }
    
    public final View getBodyView() {
        return super.zzp("1002");
    }
    
    public final View getCallToActionView() {
        return super.zzp("1003");
    }
    
    public final View getHeadlineView() {
        return super.zzp("1001");
    }
    
    public final View getImageView() {
        return super.zzp("1005");
    }
    
    public final View getLogoView() {
        return super.zzp("1006");
    }
    
    public final MediaView getMediaView() {
        final View zzp = super.zzp("1009");
        if (zzp instanceof MediaView) {
            return (MediaView)zzp;
        }
        if (zzp != null) {
            zzane.zzck("View is not an instance of MediaView");
        }
        return null;
    }
    
    public final void setAdvertiserView(final View view) {
        super.zza("1004", view);
    }
    
    public final void setBodyView(final View view) {
        super.zza("1002", view);
    }
    
    public final void setCallToActionView(final View view) {
        super.zza("1003", view);
    }
    
    public final void setHeadlineView(final View view) {
        super.zza("1001", view);
    }
    
    public final void setImageView(final View view) {
        super.zza("1005", view);
    }
    
    public final void setLogoView(final View view) {
        super.zza("1006", view);
    }
    
    public final void setMediaView(final MediaView mediaView) {
        super.zza("1009", (View)mediaView);
    }
}
