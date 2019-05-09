// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.internal.ads.zzane;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;

public final class NativeAppInstallAdView extends NativeAdView
{
    public NativeAppInstallAdView(final Context context) {
        super(context);
    }
    
    public NativeAppInstallAdView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public NativeAppInstallAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public NativeAppInstallAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    public final View getBodyView() {
        return super.zzp("2004");
    }
    
    public final View getCallToActionView() {
        return super.zzp("2002");
    }
    
    public final View getHeadlineView() {
        return super.zzp("2001");
    }
    
    public final View getIconView() {
        return super.zzp("2003");
    }
    
    public final View getImageView() {
        return super.zzp("2007");
    }
    
    public final MediaView getMediaView() {
        final View zzp = super.zzp("2011");
        if (zzp instanceof MediaView) {
            return (MediaView)zzp;
        }
        if (zzp != null) {
            zzane.zzck("View is not an instance of MediaView");
        }
        return null;
    }
    
    public final View getPriceView() {
        return super.zzp("2006");
    }
    
    public final View getStarRatingView() {
        return super.zzp("2008");
    }
    
    public final View getStoreView() {
        return super.zzp("2005");
    }
    
    public final void setBodyView(final View view) {
        super.zza("2004", view);
    }
    
    public final void setCallToActionView(final View view) {
        super.zza("2002", view);
    }
    
    public final void setHeadlineView(final View view) {
        super.zza("2001", view);
    }
    
    public final void setIconView(final View view) {
        super.zza("2003", view);
    }
    
    public final void setImageView(final View view) {
        super.zza("2007", view);
    }
    
    public final void setMediaView(final MediaView mediaView) {
        super.zza("2011", (View)mediaView);
    }
    
    public final void setPriceView(final View view) {
        super.zza("2006", view);
    }
    
    public final void setStarRatingView(final View view) {
        super.zza("2008", view);
    }
    
    public final void setStoreView(final View view) {
        super.zza("2005", view);
    }
}
