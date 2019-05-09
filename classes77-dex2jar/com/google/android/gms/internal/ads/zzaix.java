// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;

@zzadh
public final class zzaix implements zzft
{
    private final Object mLock;
    private final Context zzatx;
    private boolean zzcno;
    private String zzye;
    
    public zzaix(final Context context, final String zzye) {
        Context applicationContext = context;
        if (context.getApplicationContext() != null) {
            applicationContext = context.getApplicationContext();
        }
        this.zzatx = applicationContext;
        this.zzye = zzye;
        this.zzcno = false;
        this.mLock = new Object();
    }
    
    public final void setAdUnitId(final String zzye) {
        this.zzye = zzye;
    }
    
    @Override
    public final void zza(final zzfs zzfs) {
        this.zzx(zzfs.zztg);
    }
    
    public final void zzx(final boolean zzcno) {
        if (!zzbv.zzfh().zzs(this.zzatx)) {
            return;
        }
        synchronized (this.mLock) {
            if (this.zzcno == zzcno) {
                return;
            }
        }
        this.zzcno = zzcno;
        if (TextUtils.isEmpty((CharSequence)this.zzye)) {
            // monitorexit(o)
            return;
        }
        if (this.zzcno) {
            zzbv.zzfh().zzb(this.zzatx, this.zzye);
        }
        else {
            zzbv.zzfh().zzc(this.zzatx, this.zzye);
        }
    }
    // monitorexit(o)
}
