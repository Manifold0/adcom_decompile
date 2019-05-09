// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.search;

import com.google.android.gms.internal.ads.zzane;
import android.view.View;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.ads.zzly;
import com.google.android.gms.internal.ads.zzadh;
import android.view.ViewGroup;

@zzadh
public final class SearchAdView extends ViewGroup
{
    private final zzly zzut;
    
    public SearchAdView(final Context context) {
        super(context);
        this.zzut = new zzly(this);
    }
    
    public SearchAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzut = new zzly(this, set, false);
    }
    
    public SearchAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzut = new zzly(this, set, false);
    }
    
    public final void destroy() {
        this.zzut.destroy();
    }
    
    public final AdListener getAdListener() {
        return this.zzut.getAdListener();
    }
    
    public final AdSize getAdSize() {
        return this.zzut.getAdSize();
    }
    
    public final String getAdUnitId() {
        return this.zzut.getAdUnitId();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(final DynamicHeightSearchAdRequest dynamicHeightSearchAdRequest) {
        if (!AdSize.SEARCH.equals(this.getAdSize())) {
            throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
        }
        this.zzut.zza(dynamicHeightSearchAdRequest.zzay());
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(final SearchAdRequest searchAdRequest) {
        this.zzut.zza(searchAdRequest.zzay());
    }
    
    protected final void onLayout(final boolean b, int n, int n2, final int n3, final int n4) {
        final View child = this.getChildAt(0);
        if (child != null && child.getVisibility() != 8) {
            final int measuredWidth = child.getMeasuredWidth();
            final int measuredHeight = child.getMeasuredHeight();
            n = (n3 - n - measuredWidth) / 2;
            n2 = (n4 - n2 - measuredHeight) / 2;
            child.layout(n, n2, measuredWidth + n, measuredHeight + n2);
        }
    }
    
    protected final void onMeasure(final int n, final int n2) {
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
    
    public final void pause() {
        this.zzut.pause();
    }
    
    public final void resume() {
        this.zzut.resume();
    }
    
    public final void setAdListener(final AdListener adListener) {
        this.zzut.setAdListener(adListener);
    }
    
    public final void setAdSize(final AdSize adSize) {
        this.zzut.setAdSizes(adSize);
    }
    
    public final void setAdUnitId(final String adUnitId) {
        this.zzut.setAdUnitId(adUnitId);
    }
}
