// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.Collections;
import java.util.Map;
import android.net.Uri;
import android.text.TextUtils;

public abstract class zzr<T> implements Comparable<zzr<T>>
{
    private final Object mLock;
    private final zzaf.zza zzae;
    private final int zzaf;
    private final String zzag;
    private final int zzah;
    private zzy zzai;
    private Integer zzaj;
    private zzv zzak;
    private boolean zzal;
    private boolean zzam;
    private boolean zzan;
    private boolean zzao;
    private zzab zzap;
    private zzc zzaq;
    private zzt zzar;
    
    public zzr(int hashCode, String host, final zzy zzai) {
        zzaf.zza zzae;
        if (com.google.android.gms.internal.ads.zzaf.zza.zzbk) {
            zzae = new zzaf.zza();
        }
        else {
            zzae = null;
        }
        this.zzae = zzae;
        this.mLock = new Object();
        this.zzal = true;
        this.zzam = false;
        this.zzan = false;
        this.zzao = false;
        this.zzaq = null;
        this.zzaf = hashCode;
        this.zzag = host;
        this.zzai = zzai;
        this.zzap = new zzh();
        while (true) {
            Label_0129: {
                if (TextUtils.isEmpty((CharSequence)host)) {
                    break Label_0129;
                }
                final Uri parse = Uri.parse(host);
                if (parse == null) {
                    break Label_0129;
                }
                host = parse.getHost();
                if (host == null) {
                    break Label_0129;
                }
                hashCode = host.hashCode();
                this.zzah = hashCode;
                return;
            }
            hashCode = 0;
            continue;
        }
    }
    
    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }
    
    public final int getMethod() {
        return this.zzaf;
    }
    
    public final String getUrl() {
        return this.zzag;
    }
    
    public final boolean isCanceled() {
        synchronized (this.mLock) {
            // monitorexit(this.mLock)
            return false;
        }
    }
    
    @Override
    public String toString() {
        final String value = String.valueOf(Integer.toHexString(this.zzah));
        String concat;
        if (value.length() != 0) {
            concat = "0x".concat(value);
        }
        else {
            concat = new String("0x");
        }
        final String zzag = this.zzag;
        final String value2 = String.valueOf(zzu.zzaw);
        final String value3 = String.valueOf(this.zzaj);
        return new StringBuilder(String.valueOf("[ ] ").length() + 3 + String.valueOf(zzag).length() + String.valueOf(concat).length() + String.valueOf(value2).length() + String.valueOf(value3).length()).append("[ ] ").append(zzag).append(" ").append(concat).append(" ").append(value2).append(" ").append(value3).toString();
    }
    
    public final zzr<?> zza(final int n) {
        this.zzaj = n;
        return this;
    }
    
    public final zzr<?> zza(final zzc zzaq) {
        this.zzaq = zzaq;
        return this;
    }
    
    public final zzr<?> zza(final zzv zzak) {
        this.zzak = zzak;
        return this;
    }
    
    protected abstract zzx<T> zza(final zzp p0);
    
    final void zza(final zzt zzar) {
        synchronized (this.mLock) {
            this.zzar = zzar;
        }
    }
    
    final void zza(final zzx<?> zzx) {
        synchronized (this.mLock) {
            final zzt zzar = this.zzar;
            // monitorexit(this.mLock)
            if (zzar != null) {
                zzar.zza(this, zzx);
            }
        }
    }
    
    protected abstract void zza(final T p0);
    
    public final void zzb(final zzae zzae) {
        synchronized (this.mLock) {
            final zzy zzai = this.zzai;
            // monitorexit(this.mLock)
            if (zzai != null) {
                zzai.zzd(zzae);
            }
        }
    }
    
    public final void zzb(final String s) {
        if (com.google.android.gms.internal.ads.zzaf.zza.zzbk) {
            this.zzae.zza(s, Thread.currentThread().getId());
        }
    }
    
    final void zzc(final String s) {
        if (this.zzak != null) {
            this.zzak.zzf((zzr<Object>)this);
        }
        if (com.google.android.gms.internal.ads.zzaf.zza.zzbk) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.zzae.zza(s, id);
                this.zzae.zzc(this.toString());
                return;
            }
            new Handler(Looper.getMainLooper()).post((Runnable)new zzs(this, s, id));
        }
    }
    
    public final int zze() {
        return this.zzah;
    }
    
    public final zzc zzf() {
        return this.zzaq;
    }
    
    public byte[] zzg() throws zza {
        return null;
    }
    
    public final boolean zzh() {
        return this.zzal;
    }
    
    public final int zzi() {
        return this.zzap.zzc();
    }
    
    public final zzab zzj() {
        return this.zzap;
    }
    
    public final void zzk() {
        synchronized (this.mLock) {
            this.zzan = true;
        }
    }
    
    public final boolean zzl() {
        synchronized (this.mLock) {
            return this.zzan;
        }
    }
    
    final void zzm() {
        synchronized (this.mLock) {
            final zzt zzar = this.zzar;
            // monitorexit(this.mLock)
            if (zzar != null) {
                zzar.zza(this);
            }
        }
    }
}
