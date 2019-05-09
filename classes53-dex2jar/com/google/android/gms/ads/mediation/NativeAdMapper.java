// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

import java.util.Map;
import com.google.android.gms.ads.VideoController;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public class NativeAdMapper
{
    protected View mAdChoicesContent;
    protected Bundle mExtras;
    protected boolean mOverrideClickHandling;
    protected boolean mOverrideImpressionRecording;
    private VideoController zzasv;
    private View zzdfm;
    private boolean zzdfn;
    
    public NativeAdMapper() {
        this.mExtras = new Bundle();
    }
    
    public View getAdChoicesContent() {
        return this.mAdChoicesContent;
    }
    
    public final Bundle getExtras() {
        return this.mExtras;
    }
    
    public final boolean getOverrideClickHandling() {
        return this.mOverrideClickHandling;
    }
    
    public final boolean getOverrideImpressionRecording() {
        return this.mOverrideImpressionRecording;
    }
    
    public final VideoController getVideoController() {
        return this.zzasv;
    }
    
    public void handleClick(final View view) {
    }
    
    public boolean hasVideoContent() {
        return this.zzdfn;
    }
    
    public void recordImpression() {
    }
    
    public void setAdChoicesContent(final View mAdChoicesContent) {
        this.mAdChoicesContent = mAdChoicesContent;
    }
    
    public final void setExtras(final Bundle mExtras) {
        this.mExtras = mExtras;
    }
    
    public void setHasVideoContent(final boolean zzdfn) {
        this.zzdfn = zzdfn;
    }
    
    public void setMediaView(final View zzdfm) {
        this.zzdfm = zzdfm;
    }
    
    public final void setOverrideClickHandling(final boolean mOverrideClickHandling) {
        this.mOverrideClickHandling = mOverrideClickHandling;
    }
    
    public final void setOverrideImpressionRecording(final boolean mOverrideImpressionRecording) {
        this.mOverrideImpressionRecording = mOverrideImpressionRecording;
    }
    
    @Deprecated
    public void trackView(final View view) {
    }
    
    public void trackViews(final View view, final Map<String, View> map, final Map<String, View> map2) {
    }
    
    public void untrackView(final View view) {
    }
    
    public final void zza(final VideoController zzasv) {
        this.zzasv = zzasv;
    }
    
    public final View zzvy() {
        return this.zzdfm;
    }
}
