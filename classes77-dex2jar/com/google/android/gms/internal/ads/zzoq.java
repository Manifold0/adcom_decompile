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
public final class zzoq extends zzqp implements zzpc
{
    private Bundle mExtras;
    private Object mLock;
    private String zzbhw;
    private List<zzon> zzbhx;
    private String zzbhy;
    private String zzbia;
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
    private zzpw zzbil;
    private String zzbim;
    
    public zzoq(final String zzbhw, final List<zzon> zzbhx, final String zzbhy, final zzpw zzbil, final String zzbia, final String zzbim, @Nullable final zzoj zzbie, final Bundle mExtras, final zzlo zzbif, final View zzbig, final IObjectWrapper zzbih, final String zzbii) {
        this.mLock = new Object();
        this.zzbhw = zzbhw;
        this.zzbhx = zzbhx;
        this.zzbhy = zzbhy;
        this.zzbil = zzbil;
        this.zzbia = zzbia;
        this.zzbim = zzbim;
        this.zzbie = zzbie;
        this.mExtras = mExtras;
        this.zzbif = zzbif;
        this.zzbig = zzbig;
        this.zzbih = zzbih;
        this.zzbii = zzbii;
    }
    
    public final void destroy() {
        zzakk.zzcrm.post((Runnable)new zzor(this));
        this.zzbhw = null;
        this.zzbhx = null;
        this.zzbhy = null;
        this.zzbil = null;
        this.zzbia = null;
        this.zzbim = null;
        this.zzbie = null;
        this.mExtras = null;
        this.mLock = null;
        this.zzbif = null;
        this.zzbig = null;
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
    
    public final void zzb(final zzoz zzbij) {
        synchronized (this.mLock) {
            this.zzbij = zzbij;
        }
    }
    
    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap((Object)this.zzbij);
    }
    
    public final String zzkb() {
        return "1";
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
    
    public final zzpw zzkg() {
        return this.zzbil;
    }
}
