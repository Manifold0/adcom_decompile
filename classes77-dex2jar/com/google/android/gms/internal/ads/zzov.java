// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.view.View;
import android.support.annotation.Nullable;
import java.util.List;
import android.os.Bundle;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzov extends zzrs implements zzpc
{
    private Bundle mExtras;
    private Object mLock;
    private String zzbhw;
    private List<zzon> zzbhx;
    private String zzbhy;
    private zzpw zzbhz;
    private String zzbia;
    private double zzbib;
    private String zzbic;
    private String zzbid;
    @Nullable
    private zzoj zzbie;
    @Nullable
    private zzlo zzbif;
    @Nullable
    private View zzbig;
    @Nullable
    private IObjectWrapper zzbih;
    @Nullable
    private String zzbii;
    private zzoz zzbij;
    private String zzbim;
    
    public zzov(final String zzbhw, final List<zzon> zzbhx, final String zzbhy, final zzpw zzbhz, final String zzbia, final String zzbim, final double zzbib, final String zzbic, final String zzbid, @Nullable final zzoj zzbie, final zzlo zzbif, final View zzbig, final IObjectWrapper zzbih, final String zzbii, final Bundle mExtras) {
        this.mLock = new Object();
        this.zzbhw = zzbhw;
        this.zzbhx = zzbhx;
        this.zzbhy = zzbhy;
        this.zzbhz = zzbhz;
        this.zzbia = zzbia;
        this.zzbim = zzbim;
        this.zzbib = zzbib;
        this.zzbic = zzbic;
        this.zzbid = zzbid;
        this.zzbie = zzbie;
        this.zzbif = zzbif;
        this.zzbig = zzbig;
        this.zzbih = zzbih;
        this.zzbii = zzbii;
        this.mExtras = mExtras;
    }
    
    public final void cancelUnconfirmedClick() {
        this.zzbij.cancelUnconfirmedClick();
    }
    
    public final void destroy() {
        zzakk.zzcrm.post((Runnable)new zzow(this));
    }
    
    public final String getAdvertiser() {
        return this.zzbim;
    }
    
    public final String getBody() {
        return this.zzbhy;
    }
    
    public final String getCallToAction() {
        return this.zzbia;
    }
    
    public final String getCustomTemplateId() {
        return "";
    }
    
    public final Bundle getExtras() {
        return this.mExtras;
    }
    
    public final String getHeadline() {
        return this.zzbhw;
    }
    
    public final List getImages() {
        return this.zzbhx;
    }
    
    @Nullable
    public final String getMediationAdapterClassName() {
        return this.zzbii;
    }
    
    public final String getPrice() {
        return this.zzbid;
    }
    
    public final double getStarRating() {
        return this.zzbib;
    }
    
    public final String getStore() {
        return this.zzbic;
    }
    
    public final zzlo getVideoController() {
        return this.zzbif;
    }
    
    public final void performClick(final Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzakb.e("#001 Attempt to perform click before app native ad initialized.");
                return;
            }
            this.zzbij.performClick(bundle);
        }
    }
    
    public final boolean recordImpression(final Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzakb.e("#002 Attempt to record impression before native ad initialized.");
                return false;
            }
            return this.zzbij.recordImpression(bundle);
        }
    }
    
    public final void reportTouchEvent(final Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzakb.e("#003 Attempt to report touch event before native ad initialized.");
                return;
            }
            this.zzbij.reportTouchEvent(bundle);
        }
    }
    
    public final void zza(final zzro zzro) {
        this.zzbij.zza(zzro);
    }
    
    public final void zzb(final zzoz zzbij) {
        synchronized (this.mLock) {
            this.zzbij = zzbij;
        }
    }
    
    public final zzpw zzjz() {
        return this.zzbhz;
    }
    
    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap((Object)this.zzbij);
    }
    
    public final String zzkb() {
        return "6";
    }
    
    public final zzoj zzkc() {
        return this.zzbie;
    }
    
    public final View zzkd() {
        return this.zzbig;
    }
    
    public final IObjectWrapper zzke() {
        return this.zzbih;
    }
    
    public final zzps zzkf() {
        return (zzps)this.zzbie;
    }
}
