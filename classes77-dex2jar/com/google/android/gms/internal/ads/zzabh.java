// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import javax.annotation.concurrent.GuardedBy;
import android.content.Context;

@zzadh
public abstract class zzabh extends zzajx
{
    protected final Context mContext;
    protected final Object mLock;
    protected final zzabm zzbzd;
    protected final zzaji zzbze;
    @GuardedBy("mLock")
    protected zzaej zzbzf;
    protected final Object zzbzh;
    
    protected zzabh(final Context mContext, final zzaji zzbze, final zzabm zzbzd) {
        super(true);
        this.mLock = new Object();
        this.zzbzh = new Object();
        this.mContext = mContext;
        this.zzbze = zzbze;
        this.zzbzf = zzbze.zzcos;
        this.zzbzd = zzbzd;
    }
    
    @Override
    public void onStop() {
    }
    
    protected abstract zzajh zzaa(final int p0);
    
    @Override
    public final void zzdn() {
    Label_0094_Outer:
        while (true) {
            while (true) {
                int n;
                synchronized (this.mLock) {
                    zzakb.zzck("AdRendererBackgroundTask started.");
                    n = this.zzbze.errorCode;
                    while (true) {
                        try {
                            this.zze(SystemClock.elapsedRealtime());
                            zzakk.zzcrm.post((Runnable)new zzabj(this, this.zzaa(n)));
                            return;
                        }
                        catch (zzabk zzabk) {
                            n = zzabk.getErrorCode();
                            if (n == 3 || n == -1) {
                                zzakb.zzdj(zzabk.getMessage());
                            }
                            else {
                                zzakb.zzdk(zzabk.getMessage());
                            }
                            if (this.zzbzf == null) {
                                this.zzbzf = new zzaej(n);
                                zzakk.zzcrm.post((Runnable)new zzabi(this));
                                continue Label_0094_Outer;
                            }
                        }
                        break;
                    }
                }
                this.zzbzf = new zzaej(n, this.zzbzf.zzbsu);
                continue;
            }
        }
    }
    
    protected abstract void zze(final long p0) throws zzabk;
}
