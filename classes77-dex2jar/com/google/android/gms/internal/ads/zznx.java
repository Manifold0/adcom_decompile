// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import android.support.annotation.Nullable;
import java.util.Map;
import java.util.List;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznx
{
    private final Object mLock;
    @VisibleForTesting
    private boolean zzbgn;
    private final List<zznv> zzbgo;
    private final Map<String, String> zzbgp;
    private String zzbgq;
    @Nullable
    private zznx zzbgr;
    
    public zznx(final boolean zzbgn, final String s, final String s2) {
        this.zzbgo = new LinkedList<zznv>();
        this.zzbgp = new LinkedHashMap<String, String>();
        this.mLock = new Object();
        this.zzbgn = zzbgn;
        this.zzbgp.put("action", s);
        this.zzbgp.put("ad_format", s2);
    }
    
    public final boolean zza(final zznv zznv, final long n, final String... array) {
        synchronized (this.mLock) {
            for (int length = array.length, i = 0; i < length; ++i) {
                this.zzbgo.add(new zznv(n, array[i], zznv));
            }
            return true;
        }
    }
    
    public final boolean zza(@Nullable final zznv zznv, final String... array) {
        return this.zzbgn && zznv != null && this.zza(zznv, zzbv.zzer().elapsedRealtime(), array);
    }
    
    public final void zzan(final String zzbgq) {
        if (!this.zzbgn) {
            return;
        }
        synchronized (this.mLock) {
            this.zzbgq = zzbgq;
        }
    }
    
    public final void zzc(@Nullable final zznx zzbgr) {
        synchronized (this.mLock) {
            this.zzbgr = zzbgr;
        }
    }
    
    @Nullable
    public final zznv zzd(final long n) {
        if (!this.zzbgn) {
            return null;
        }
        return new zznv(n, null, null);
    }
    
    public final void zze(final String s, final String s2) {
        if (this.zzbgn && !TextUtils.isEmpty((CharSequence)s2)) {
            final zznn zzpy = zzbv.zzeo().zzpy();
            if (zzpy != null) {
                synchronized (this.mLock) {
                    final zznr zzal = zzpy.zzal(s);
                    final Map<String, String> zzbgp = this.zzbgp;
                    zzbgp.put(s, zzal.zzd(zzbgp.get(s), s2));
                }
            }
        }
    }
    
    public final zznv zzjj() {
        return this.zzd(zzbv.zzer().elapsedRealtime());
    }
    
    public final String zzjk() {
        final StringBuilder sb = new StringBuilder();
        synchronized (this.mLock) {
            for (final zznv zznv : this.zzbgo) {
                final long time = zznv.getTime();
                final String zzjg = zznv.zzjg();
                final zznv zzjh = zznv.zzjh();
                if (zzjh != null && time > 0L) {
                    sb.append(zzjg).append('.').append(time - zzjh.getTime()).append(',');
                }
            }
        }
        this.zzbgo.clear();
        final StringBuilder sb2;
        if (!TextUtils.isEmpty((CharSequence)this.zzbgq)) {
            sb2.append(this.zzbgq);
        }
        else if (sb2.length() > 0) {
            sb2.setLength(sb2.length() - 1);
        }
        // monitorexit(o)
        return sb2.toString();
    }
    
    @VisibleForTesting
    final Map<String, String> zzjl() {
        synchronized (this.mLock) {
            final zznn zzpy = zzbv.zzeo().zzpy();
            if (zzpy == null || this.zzbgr == null) {
                return this.zzbgp;
            }
            return zzpy.zza(this.zzbgp, this.zzbgr.zzjl());
        }
    }
    
    public final zznv zzjm() {
        synchronized (this.mLock) {
            // monitorexit(this.mLock)
            return null;
        }
    }
}
