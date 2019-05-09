// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzxa extends zzxu
{
    private final Object mLock;
    @GuardedBy("mLock")
    private zzxf zzbtf;
    @GuardedBy("mLock")
    private zzwz zzbtg;
    
    public zzxa() {
        this.mLock = new Object();
    }
    
    public final void onAdClicked() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzce();
            }
        }
    }
    
    public final void onAdClosed() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcf();
            }
        }
    }
    
    public final void onAdFailedToLoad(int n) {
        while (true) {
            while (true) {
                Label_0044: {
                    synchronized (this.mLock) {
                        if (this.zzbtf != null) {
                            if (n != 3) {
                                break Label_0044;
                            }
                            n = 1;
                            this.zzbtf.zzx(n);
                            this.zzbtf = null;
                        }
                        return;
                    }
                }
                n = 2;
                continue;
            }
        }
    }
    
    public final void onAdImpression() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcj();
            }
        }
    }
    
    public final void onAdLeftApplication() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcg();
            }
        }
    }
    
    public final void onAdLoaded() {
        synchronized (this.mLock) {
            if (this.zzbtf != null) {
                this.zzbtf.zzx(0);
                this.zzbtf = null;
                return;
            }
            if (this.zzbtg != null) {
                this.zzbtg.zzci();
            }
        }
    }
    
    public final void onAdOpened() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzch();
            }
        }
    }
    
    public final void onAppEvent(final String s, final String s2) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzb(s, s2);
            }
        }
    }
    
    public final void onVideoEnd() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcd();
            }
        }
    }
    
    public final void zza(@Nullable final zzwz zzbtg) {
        synchronized (this.mLock) {
            this.zzbtg = zzbtg;
        }
    }
    
    public final void zza(final zzxf zzbtf) {
        synchronized (this.mLock) {
            this.zzbtf = zzbtf;
        }
    }
    
    public final void zza(final zzxw zzxw) {
        synchronized (this.mLock) {
            if (this.zzbtf != null) {
                this.zzbtf.zza(0, zzxw);
                this.zzbtf = null;
                return;
            }
            if (this.zzbtg != null) {
                this.zzbtg.zzci();
            }
        }
    }
    
    public final void zzb(final zzqs zzqs, final String s) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zza(zzqs, s);
            }
        }
    }
    
    public final void zzbj(final String s) {
    }
}
