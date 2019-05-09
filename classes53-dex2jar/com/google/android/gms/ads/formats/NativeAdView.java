// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.common.internal.Preconditions;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.ads.zzqa;
import android.widget.FrameLayout;

public class NativeAdView extends FrameLayout
{
    private final FrameLayout zzvh;
    private final zzqa zzvi;
    
    public NativeAdView(final Context context) {
        super(context);
        this.zzvh = this.zzb(context);
        this.zzvi = this.zzbf();
    }
    
    public NativeAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzvh = this.zzb(context);
        this.zzvi = this.zzbf();
    }
    
    public NativeAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzvh = this.zzb(context);
        this.zzvi = this.zzbf();
    }
    
    @TargetApi(21)
    public NativeAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.zzvh = this.zzb(context);
        this.zzvi = this.zzbf();
    }
    
    private final FrameLayout zzb(final Context context) {
        final FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.addView((View)frameLayout);
        return frameLayout;
    }
    
    private final zzqa zzbf() {
        Preconditions.checkNotNull((Object)this.zzvh, (Object)"createDelegate must be called after mOverlayFrame has been created");
        if (this.isInEditMode()) {
            return null;
        }
        return zzkb.zzig().zza(this.zzvh.getContext(), this, this.zzvh);
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.addView(view, n, viewGroup$LayoutParams);
        super.bringChildToFront((View)this.zzvh);
    }
    
    public void bringChildToFront(final View view) {
        super.bringChildToFront(view);
        if (this.zzvh != view) {
            super.bringChildToFront((View)this.zzvh);
        }
    }
    
    public void destroy() {
        try {
            this.zzvi.destroy();
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to destroy native ad view", (Throwable)ex);
        }
    }
    
    public AdChoicesView getAdChoicesView() {
        final View zzp = this.zzp("1098");
        if (zzp instanceof AdChoicesView) {
            return (AdChoicesView)zzp;
        }
        return null;
    }
    
    public void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        if (this.zzvi == null) {
            return;
        }
        try {
            this.zzvi.zzb(ObjectWrapper.wrap((Object)view), n);
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call onVisibilityChanged on delegate", (Throwable)ex);
        }
    }
    
    public void removeAllViews() {
        super.removeAllViews();
        super.addView((View)this.zzvh);
    }
    
    public void removeView(final View view) {
        if (this.zzvh == view) {
            return;
        }
        super.removeView(view);
    }
    
    public void setAdChoicesView(final AdChoicesView adChoicesView) {
        this.zza("1098", (View)adChoicesView);
    }
    
    public void setNativeAd(final NativeAd nativeAd) {
        try {
            this.zzvi.zza((IObjectWrapper)nativeAd.zzbe());
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call setNativeAd on delegate", (Throwable)ex);
        }
    }
    
    protected final void zza(final String s, final View view) {
        try {
            this.zzvi.zzb(s, ObjectWrapper.wrap((Object)view));
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call setAssetView on delegate", (Throwable)ex);
        }
    }
    
    protected final View zzp(final String s) {
        try {
            final IObjectWrapper zzak = this.zzvi.zzak(s);
            if (zzak != null) {
                return (View)ObjectWrapper.unwrap(zzak);
            }
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call getAssetView on delegate", (Throwable)ex);
        }
        return null;
    }
}
