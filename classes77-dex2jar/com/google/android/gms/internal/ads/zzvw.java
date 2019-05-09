// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

public final class zzvw extends zzaop<zzuu>
{
    private final Object mLock;
    private zzalo<zzuu> zzbpz;
    private boolean zzbqt;
    private int zzbqu;
    
    public zzvw(final zzalo<zzuu> zzbpz) {
        this.mLock = new Object();
        this.zzbpz = zzbpz;
        this.zzbqt = false;
        this.zzbqu = 0;
    }
    
    private final void zzmc() {
        while (true) {
            while (true) {
                synchronized (this.mLock) {
                    if (this.zzbqu >= 0) {
                        final boolean b = true;
                        Preconditions.checkState(b);
                        if (this.zzbqt && this.zzbqu == 0) {
                            zzakb.v("No reference is left (including root). Cleaning up engine.");
                            this.zza(new zzvz(this), new zzaon());
                        }
                        else {
                            zzakb.v("There are still references to the engine. Not destroying.");
                        }
                        return;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    public final zzvs zzlz() {
        while (true) {
            final zzvs zzvs = new zzvs(this);
            while (true) {
                synchronized (this.mLock) {
                    this.zza(new zzvx(this, zzvs), new zzvy(this, zzvs));
                    if (this.zzbqu >= 0) {
                        final boolean b = true;
                        Preconditions.checkState(b);
                        ++this.zzbqu;
                        return zzvs;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    protected final void zzma() {
        while (true) {
            while (true) {
                synchronized (this.mLock) {
                    if (this.zzbqu > 0) {
                        final boolean b = true;
                        Preconditions.checkState(b);
                        zzakb.v("Releasing 1 reference for JS Engine");
                        --this.zzbqu;
                        this.zzmc();
                        return;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    public final void zzmb() {
        while (true) {
            boolean b = true;
            while (true) {
                synchronized (this.mLock) {
                    if (this.zzbqu >= 0) {
                        Preconditions.checkState(b);
                        zzakb.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
                        this.zzbqt = true;
                        this.zzmc();
                        return;
                    }
                }
                b = false;
                continue;
            }
        }
    }
}
