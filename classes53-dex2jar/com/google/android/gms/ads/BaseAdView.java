// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzane;
import android.view.View;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.ads.zzly;
import android.view.ViewGroup;

class BaseAdView extends ViewGroup
{
    protected final zzly zzut;
    
    public BaseAdView(final Context context, final int n) {
        super(context);
        this.zzut = new zzly(this, n);
    }
    
    public BaseAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set);
        this.zzut = new zzly(this, set, false, n);
    }
    
    public BaseAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n);
        this.zzut = new zzly(this, set, false, n2);
    }
    
    public void destroy() {
        this.zzut.destroy();
    }
    
    public AdListener getAdListener() {
        return this.zzut.getAdListener();
    }
    
    public AdSize getAdSize() {
        return this.zzut.getAdSize();
    }
    
    public String getAdUnitId() {
        return this.zzut.getAdUnitId();
    }
    
    public String getMediationAdapterClassName() {
        return this.zzut.getMediationAdapterClassName();
    }
    
    public boolean isLoading() {
        return this.zzut.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final AdRequest adRequest) {
        this.zzut.zza(adRequest.zzay());
    }
    
    protected void onLayout(final boolean b, int n, int n2, final int n3, final int n4) {
        final View child = this.getChildAt(0);
        if (child != null && child.getVisibility() != 8) {
            final int measuredWidth = child.getMeasuredWidth();
            final int measuredHeight = child.getMeasuredHeight();
            n = (n3 - n - measuredWidth) / 2;
            n2 = (n4 - n2 - measuredHeight) / 2;
            child.layout(n, n2, measuredWidth + n, measuredHeight + n2);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        int n3 = 0;
        final View child = this.getChildAt(0);
        int n4 = 0;
        Label_0045: {
            if (child != null && child.getVisibility() != 8) {
                this.measureChild(child, n, n2);
                n4 = child.getMeasuredWidth();
                n3 = child.getMeasuredHeight();
            }
            else {
                while (true) {
                    try {
                        final AdSize adSize = this.getAdSize();
                        if (adSize != null) {
                            final Context context = this.getContext();
                            n4 = adSize.getWidthInPixels(context);
                            n3 = adSize.getHeightInPixels(context);
                            break Label_0045;
                        }
                    }
                    catch (NullPointerException ex) {
                        zzane.zzb("Unable to retrieve ad size.", ex);
                        final AdSize adSize = null;
                        continue;
                    }
                    break;
                }
                n4 = 0;
            }
        }
        this.setMeasuredDimension(View.resolveSize(Math.max(n4, this.getSuggestedMinimumWidth()), n), View.resolveSize(Math.max(n3, this.getSuggestedMinimumHeight()), n2));
    }
    
    public void pause() {
        this.zzut.pause();
    }
    
    public void resume() {
        this.zzut.resume();
    }
    
    public void setAdListener(final AdListener adListener) {
        this.zzut.setAdListener(adListener);
        if (adListener == null) {
            this.zzut.zza((zzjd)null);
            this.zzut.setAppEventListener(null);
        }
        else {
            if (adListener instanceof zzjd) {
                this.zzut.zza((zzjd)adListener);
            }
            if (adListener instanceof AppEventListener) {
                this.zzut.setAppEventListener((AppEventListener)adListener);
            }
        }
    }
    
    public void setAdSize(final AdSize adSize) {
        this.zzut.setAdSizes(adSize);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.zzut.setAdUnitId(adUnitId);
    }
}
