// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.os.Bundle;
import java.util.Arrays;
import java.util.List;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.support.annotation.Nullable;

@zzadh
public final class zzos extends zzqt implements zzpb
{
    private final Object mLock;
    private final zzoj zzbie;
    @Nullable
    private zzlo zzbif;
    @Nullable
    private View zzbig;
    private zzoz zzbij;
    private final String zzbio;
    private final SimpleArrayMap<String, zzon> zzbip;
    private final SimpleArrayMap<String, String> zzbiq;
    
    public zzos(final String zzbio, final SimpleArrayMap<String, zzon> zzbip, final SimpleArrayMap<String, String> zzbiq, final zzoj zzbie, final zzlo zzbif, final View zzbig) {
        this.mLock = new Object();
        this.zzbio = zzbio;
        this.zzbip = zzbip;
        this.zzbiq = zzbiq;
        this.zzbie = zzbie;
        this.zzbif = zzbif;
        this.zzbig = zzbig;
    }
    
    public final void destroy() {
        zzakk.zzcrm.post((Runnable)new zzou(this));
        this.zzbif = null;
        this.zzbig = null;
    }
    
    public final List<String> getAvailableAssetNames() {
        final int n = 0;
        final String[] array = new String[this.zzbip.size() + this.zzbiq.size()];
        int n2 = 0;
        int n3 = 0;
        int i;
        int n4;
        while (true) {
            i = n;
            n4 = n3;
            if (n2 >= this.zzbip.size()) {
                break;
            }
            array[n3] = (String)this.zzbip.keyAt(n2);
            ++n3;
            ++n2;
        }
        while (i < this.zzbiq.size()) {
            array[n4] = (String)this.zzbiq.keyAt(i);
            ++i;
            ++n4;
        }
        return Arrays.asList(array);
    }
    
    public final String getCustomTemplateId() {
        return this.zzbio;
    }
    
    public final zzlo getVideoController() {
        return this.zzbif;
    }
    
    public final void performClick(final String s) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzane.e("#001 Attempt to perform click before app native ad initialized.");
                return;
            }
            this.zzbij.zza(null, s, null, null, null);
        }
    }
    
    public final void recordImpression() {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                zzane.e("#002 Attempt to record impression before native ad initialized.");
                return;
            }
            this.zzbij.zza(null, (Map<String, WeakReference<View>>)null);
        }
    }
    
    public final String zzao(final String s) {
        return (String)this.zzbiq.get((Object)s);
    }
    
    public final zzpw zzap(final String s) {
        return (zzpw)this.zzbip.get((Object)s);
    }
    
    public final void zzb(final zzoz zzbij) {
        synchronized (this.mLock) {
            this.zzbij = zzbij;
        }
    }
    
    public final boolean zzh(final IObjectWrapper objectWrapper) {
        if (this.zzbij == null) {
            zzane.e("Attempt to call renderVideoInMediaView before ad initialized.");
        }
        else if (this.zzbig != null) {
            this.zzbij.zza((View)ObjectWrapper.unwrap(objectWrapper), new zzot(this));
            return true;
        }
        return false;
    }
    
    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap((Object)this.zzbij);
    }
    
    public final String zzkb() {
        return "3";
    }
    
    public final zzoj zzkc() {
        return this.zzbie;
    }
    
    public final View zzkd() {
        return this.zzbig;
    }
    
    public final IObjectWrapper zzkh() {
        return ObjectWrapper.wrap((Object)this.zzbij.getContext().getApplicationContext());
    }
}
