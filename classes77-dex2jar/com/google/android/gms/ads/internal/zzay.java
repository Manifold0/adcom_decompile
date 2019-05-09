// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzald;
import com.google.android.gms.internal.ads.zzkb;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzaib;
import java.util.Iterator;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Collection;
import java.util.List;
import com.google.android.gms.internal.ads.zzaic;
import java.util.Map;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.internal.ads.zzagr;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzang;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzlk;

@zzadh
@ParametersAreNonnullByDefault
public final class zzay extends zzlk
{
    private static final Object sLock;
    @Nullable
    @GuardedBy("sLock")
    private static zzay zzzu;
    private final Context mContext;
    private final Object mLock;
    private boolean zzzv;
    private zzang zzzw;
    
    static {
        sLock = new Object();
    }
    
    @VisibleForTesting
    private zzay(final Context mContext, final zzang zzzw) {
        this.mLock = new Object();
        this.mContext = mContext;
        this.zzzw = zzzw;
        this.zzzv = false;
    }
    
    public static zzay zza(final Context context, final zzang zzang) {
        synchronized (zzay.sLock) {
            if (zzay.zzzu == null) {
                zzay.zzzu = new zzay(context.getApplicationContext(), zzang);
            }
            return zzay.zzzu;
        }
    }
    
    public final void setAppMuted(final boolean appMuted) {
        zzbv.zzfj().setAppMuted(appMuted);
    }
    
    public final void setAppVolume(final float appVolume) {
        zzbv.zzfj().setAppVolume(appVolume);
    }
    
    public final void zza() {
        synchronized (zzay.sLock) {
            if (this.zzzv) {
                zzakb.zzdk("Mobile ads is initialized already.");
                return;
            }
            this.zzzv = true;
            // monitorexit(zzay.sLock)
            zznk.initialize(this.mContext);
            zzbv.zzeo().zzd(this.mContext, this.zzzw);
            zzbv.zzeq().initialize(this.mContext);
        }
    }
    
    public final void zza(final String s, final IObjectWrapper objectWrapper) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            zznk.initialize(this.mContext);
            final boolean booleanValue = (boolean)zzkb.zzik().zzd(zznk.zzbcs);
            final boolean booleanValue2 = (boolean)zzkb.zzik().zzd(zznk.zzayd);
            zzaz zzaz;
            boolean b;
            if (zzkb.zzik().zzd(zznk.zzayd)) {
                zzaz = new zzaz(this, (Runnable)ObjectWrapper.unwrap(objectWrapper));
                b = true;
            }
            else {
                zzaz = null;
                b = (booleanValue | booleanValue2);
            }
            if (b) {
                zzbv.zzes().zza(this.mContext, this.zzzw, s, zzaz);
            }
        }
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final String adUnitId) {
        if (objectWrapper == null) {
            zzakb.e("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        final Context context = (Context)ObjectWrapper.unwrap(objectWrapper);
        if (context == null) {
            zzakb.e("Context is null. Failed to open debug menu.");
            return;
        }
        final zzald zzald = new zzald(context);
        zzald.setAdUnitId(adUnitId);
        zzald.zzda(this.zzzw.zzcw);
        zzald.showDialog();
    }
    
    public final float zzdo() {
        return zzbv.zzfj().zzdo();
    }
    
    public final boolean zzdp() {
        return zzbv.zzfj().zzdp();
    }
    
    public final void zzt(final String s) {
        zznk.initialize(this.mContext);
        if (!TextUtils.isEmpty((CharSequence)s) && (boolean)zzkb.zzik().zzd(zznk.zzbcs)) {
            zzbv.zzes().zza(this.mContext, this.zzzw, s, null);
        }
    }
}
