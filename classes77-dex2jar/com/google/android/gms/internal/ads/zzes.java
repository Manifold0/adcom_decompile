// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import java.util.Iterator;
import java.util.Map;
import android.content.Context;
import java.util.ArrayList;
import java.util.WeakHashMap;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzes implements zzfa
{
    private final Object mLock;
    private final WeakHashMap<zzajh, zzet> zzaem;
    private final ArrayList<zzet> zzaen;
    private final Context zzaeo;
    private final zzvf zzaep;
    private final zzang zzyf;
    
    public zzes(Context applicationContext, final zzang zzyf) {
        this.mLock = new Object();
        this.zzaem = new WeakHashMap<zzajh, zzet>();
        this.zzaen = new ArrayList<zzet>();
        this.zzaeo = applicationContext.getApplicationContext();
        this.zzyf = zzyf;
        applicationContext = applicationContext.getApplicationContext();
        this.zzaep = new zzvf(applicationContext, zzyf, (String)zzkb.zzik().zzd(zznk.zzaub));
    }
    
    private final boolean zzg(final zzajh zzajh) {
        while (true) {
            synchronized (this.mLock) {
                final zzet zzet = this.zzaem.get(zzajh);
                if (zzet != null && zzet.zzge()) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @Override
    public final void zza(final zzet zzet) {
        synchronized (this.mLock) {
            if (!zzet.zzge()) {
                this.zzaen.remove(zzet);
                final Iterator<Map.Entry<zzajh, zzet>> iterator = this.zzaem.entrySet().iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getValue() == zzet) {
                        iterator.remove();
                    }
                }
            }
        }
    }
    // monitorexit(o)
    
    public final void zza(final zzjn zzjn, final zzajh zzajh) {
        this.zza(zzjn, zzajh, zzajh.zzbyo.getView());
    }
    
    public final void zza(final zzjn zzjn, final zzajh zzajh, final View view) {
        this.zza(zzjn, zzajh, new zzez(view, zzajh), null);
    }
    
    public final void zza(final zzjn zzjn, final zzajh zzajh, final View view, final zzaqw zzaqw) {
        this.zza(zzjn, zzajh, new zzez(view, zzajh), zzaqw);
    }
    
    public final void zza(final zzjn zzjn, final zzajh zzajh, final zzgd zzgd, @Nullable final zzaqw zzaqw) {
        while (true) {
            synchronized (this.mLock) {
                zzet zzet;
                if (this.zzg(zzajh)) {
                    zzet = this.zzaem.get(zzajh);
                }
                else {
                    zzet = new zzet(this.zzaeo, zzjn, zzajh, this.zzyf, zzgd);
                    zzet.zza(this);
                    this.zzaem.put(zzajh, zzet);
                    this.zzaen.add(zzet);
                }
                if (zzaqw != null) {
                    zzet.zza(new zzfb(zzet, zzaqw));
                    return;
                }
            }
            final zzet zzet2;
            zzet2.zza(new zzff(zzet2, this.zzaep, this.zzaeo));
        }
    }
    
    public final void zzh(final zzajh zzajh) {
        synchronized (this.mLock) {
            final zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.zzgc();
            }
        }
    }
    
    public final void zzi(final zzajh zzajh) {
        synchronized (this.mLock) {
            final zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.stop();
            }
        }
    }
    
    public final void zzj(final zzajh zzajh) {
        synchronized (this.mLock) {
            final zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.pause();
            }
        }
    }
    
    public final void zzk(final zzajh zzajh) {
        synchronized (this.mLock) {
            final zzet zzet = this.zzaem.get(zzajh);
            if (zzet != null) {
                zzet.resume();
            }
        }
    }
}
