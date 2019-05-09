// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.common.internal.Preconditions;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.view.View;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.ads.zzqa;
import android.widget.FrameLayout;

public final class UnifiedNativeAdView extends FrameLayout
{
    private final FrameLayout zzvp;
    private final zzqa zzvq;
    
    public UnifiedNativeAdView(final Context context) {
        super(context);
        this.zzvp = this.zzb(context);
        this.zzvq = this.zzbf();
    }
    
    public UnifiedNativeAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzvp = this.zzb(context);
        this.zzvq = this.zzbf();
    }
    
    public UnifiedNativeAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzvp = this.zzb(context);
        this.zzvq = this.zzbf();
    }
    
    @TargetApi(21)
    public UnifiedNativeAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.zzvp = this.zzb(context);
        this.zzvq = this.zzbf();
    }
    
    private final void zza(final String s, final View view) {
        try {
            this.zzvq.zzb(s, ObjectWrapper.wrap((Object)view));
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call setAssetView on delegate", (Throwable)ex);
        }
    }
    
    private final FrameLayout zzb(final Context context) {
        final FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.addView((View)frameLayout);
        return frameLayout;
    }
    
    private final zzqa zzbf() {
        Preconditions.checkNotNull((Object)this.zzvp, (Object)"createDelegate must be called after overlayFrame has been created");
        if (this.isInEditMode()) {
            return null;
        }
        return zzkb.zzig().zza(this.zzvp.getContext(), this, this.zzvp);
    }
    
    private final View zzp(final String s) {
        try {
            final IObjectWrapper zzak = this.zzvq.zzak(s);
            if (zzak != null) {
                return (View)ObjectWrapper.unwrap(zzak);
            }
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call getAssetView on delegate", (Throwable)ex);
        }
        return null;
    }
    
    public final void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.addView(view, n, viewGroup$LayoutParams);
        super.bringChildToFront((View)this.zzvp);
    }
    
    public final void bringChildToFront(final View view) {
        super.bringChildToFront(view);
        if (this.zzvp != view) {
            super.bringChildToFront((View)this.zzvp);
        }
    }
    
    public final void destroy() {
        try {
            this.zzvq.destroy();
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to destroy native ad view", (Throwable)ex);
        }
    }
    
    public final AdChoicesView getAdChoicesView() {
        final View zzp = this.zzp("3011");
        if (zzp instanceof AdChoicesView) {
            return (AdChoicesView)zzp;
        }
        return null;
    }
    
    public final View getAdvertiserView() {
        return this.zzp("3005");
    }
    
    public final View getBodyView() {
        return this.zzp("3004");
    }
    
    public final View getCallToActionView() {
        return this.zzp("3002");
    }
    
    public final View getHeadlineView() {
        return this.zzp("3001");
    }
    
    public final View getIconView() {
        return this.zzp("3003");
    }
    
    public final View getImageView() {
        return this.zzp("3008");
    }
    
    public final MediaView getMediaView() {
        final View zzp = this.zzp("3010");
        if (zzp instanceof MediaView) {
            return (MediaView)zzp;
        }
        if (zzp != null) {
            zzane.zzck("View is not an instance of MediaView");
        }
        return null;
    }
    
    public final View getPriceView() {
        return this.zzp("3007");
    }
    
    public final View getStarRatingView() {
        return this.zzp("3009");
    }
    
    public final View getStoreView() {
        return this.zzp("3006");
    }
    
    public final void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        if (this.zzvq == null) {
            return;
        }
        try {
            this.zzvq.zzb(ObjectWrapper.wrap((Object)view), n);
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call onVisibilityChanged on delegate", (Throwable)ex);
        }
    }
    
    public final void removeAllViews() {
        super.removeAllViews();
        super.addView((View)this.zzvp);
    }
    
    public final void removeView(final View view) {
        if (this.zzvp == view) {
            return;
        }
        super.removeView(view);
    }
    
    public final void setAdChoicesView(final AdChoicesView adChoicesView) {
        this.zza("3011", (View)adChoicesView);
    }
    
    public final void setAdvertiserView(final View view) {
        this.zza("3005", view);
    }
    
    public final void setBodyView(final View view) {
        this.zza("3004", view);
    }
    
    public final void setCallToActionView(final View view) {
        this.zza("3002", view);
    }
    
    public final void setClickConfirmingView(final View view) {
        try {
            this.zzvq.zzc(ObjectWrapper.wrap((Object)view));
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call setClickConfirmingView on delegate", (Throwable)ex);
        }
    }
    
    public final void setHeadlineView(final View view) {
        this.zza("3001", view);
    }
    
    public final void setIconView(final View view) {
        this.zza("3003", view);
    }
    
    public final void setImageView(final View view) {
        this.zza("3008", view);
    }
    
    public final void setMediaView(final MediaView mediaView) {
        this.zza("3010", (View)mediaView);
    }
    
    public final void setNativeAd(final UnifiedNativeAd unifiedNativeAd) {
        try {
            this.zzvq.zza((IObjectWrapper)unifiedNativeAd.zzbe());
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call setNativeAd on delegate", (Throwable)ex);
        }
    }
    
    public final void setPriceView(final View view) {
        this.zza("3007", view);
    }
    
    public final void setStarRatingView(final View view) {
        this.zza("3009", view);
    }
    
    public final void setStoreView(final View view) {
        this.zza("3006", view);
    }
}
