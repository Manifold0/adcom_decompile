// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.internal.ads.zzks;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.internal.ads.zzane;
import android.view.View;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.common.internal.Preconditions;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.ads.zzly;
import android.view.ViewGroup;

public final class PublisherAdView extends ViewGroup
{
    private final zzly zzut;
    
    public PublisherAdView(final Context context) {
        super(context);
        this.zzut = new zzly(this);
    }
    
    public PublisherAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzut = new zzly(this, set, true);
        Preconditions.checkNotNull((Object)context, (Object)"Context cannot be null");
    }
    
    public PublisherAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzut = new zzly(this, set, true);
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
    
    public final AdSize[] getAdSizes() {
        return this.zzut.getAdSizes();
    }
    
    public final String getAdUnitId() {
        return this.zzut.getAdUnitId();
    }
    
    public final AppEventListener getAppEventListener() {
        return this.zzut.getAppEventListener();
    }
    
    public final String getMediationAdapterClassName() {
        return this.zzut.getMediationAdapterClassName();
    }
    
    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzut.getOnCustomRenderedAdLoadedListener();
    }
    
    public final VideoController getVideoController() {
        return this.zzut.getVideoController();
    }
    
    public final VideoOptions getVideoOptions() {
        return this.zzut.getVideoOptions();
    }
    
    public final boolean isLoading() {
        return this.zzut.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.zzut.zza(publisherAdRequest.zzay());
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
    
    public final void recordManualImpression() {
        this.zzut.recordManualImpression();
    }
    
    public final void resume() {
        this.zzut.resume();
    }
    
    public final void setAdListener(final AdListener adListener) {
        this.zzut.setAdListener(adListener);
    }
    
    public final void setAdSizes(final AdSize... array) {
        if (array == null || array.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzut.zza(array);
    }
    
    public final void setAdUnitId(final String adUnitId) {
        this.zzut.setAdUnitId(adUnitId);
    }
    
    public final void setAppEventListener(final AppEventListener appEventListener) {
        this.zzut.setAppEventListener(appEventListener);
    }
    
    public final void setCorrelator(final Correlator correlator) {
        this.zzut.setCorrelator(correlator);
    }
    
    public final void setManualImpressionsEnabled(final boolean manualImpressionsEnabled) {
        this.zzut.setManualImpressionsEnabled(manualImpressionsEnabled);
    }
    
    public final void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzut.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }
    
    public final void setVideoOptions(final VideoOptions videoOptions) {
        this.zzut.setVideoOptions(videoOptions);
    }
    
    public final boolean zza(final zzks zzks) {
        return this.zzut.zza(zzks);
    }
}
