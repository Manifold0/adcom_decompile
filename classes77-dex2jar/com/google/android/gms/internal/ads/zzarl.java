// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;
import android.support.annotation.Nullable;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzarl extends zzlp
{
    private final Object lock;
    @GuardedBy("lock")
    private boolean zzato;
    @GuardedBy("lock")
    private boolean zzatp;
    @GuardedBy("lock")
    private boolean zzatq;
    @GuardedBy("lock")
    private zzlr zzbuq;
    private final zzapw zzcyg;
    private final boolean zzded;
    private final boolean zzdee;
    private final float zzdef;
    @GuardedBy("lock")
    private int zzdeg;
    @GuardedBy("lock")
    private boolean zzdeh;
    @GuardedBy("lock")
    private boolean zzdei;
    @GuardedBy("lock")
    private float zzdej;
    @GuardedBy("lock")
    private float zzdek;
    
    public zzarl(final zzapw zzcyg, final float zzdef, final boolean zzded, final boolean zzdee) {
        this.lock = new Object();
        this.zzdei = true;
        this.zzato = true;
        this.zzcyg = zzcyg;
        this.zzdef = zzdef;
        this.zzded = zzded;
        this.zzdee = zzdee;
    }
    
    private final void zzf(final String s, @Nullable final Map<String, String> map) {
        HashMap<String, String> hashMap;
        if (map == null) {
            hashMap = new HashMap<String, String>();
        }
        else {
            hashMap = new HashMap<String, String>(map);
        }
        hashMap.put("action", s);
        zzaoe.zzcvy.execute(new zzarm(this, hashMap));
    }
    
    public final float getAspectRatio() {
        synchronized (this.lock) {
            return this.zzdek;
        }
    }
    
    public final int getPlaybackState() {
        synchronized (this.lock) {
            return this.zzdeg;
        }
    }
    
    public final boolean isClickToExpandEnabled() {
        final boolean customControlsEnabled = this.isCustomControlsEnabled();
        final Object lock = this.lock;
        // monitorenter(lock)
        if (customControlsEnabled) {
            return false;
        }
        try {
            if (this.zzatq && this.zzdee) {
                return true;
            }
        }
        finally {
        }
        // monitorexit(lock)
        return false;
    }
    
    public final boolean isCustomControlsEnabled() {
        while (true) {
            synchronized (this.lock) {
                if (this.zzded && this.zzatp) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public final boolean isMuted() {
        synchronized (this.lock) {
            return this.zzdei;
        }
    }
    
    public final void mute(final boolean b) {
        String s;
        if (b) {
            s = "mute";
        }
        else {
            s = "unmute";
        }
        this.zzf(s, null);
    }
    
    public final void pause() {
        this.zzf("pause", null);
    }
    
    public final void play() {
        this.zzf("play", null);
    }
    
    public final void zza(float zzdek, final int zzdeg, final boolean zzdei, final float zzdek2) {
        synchronized (this.lock) {
            this.zzdej = zzdek;
            final boolean zzdei2 = this.zzdei;
            this.zzdei = zzdei;
            final int zzdeg2 = this.zzdeg;
            this.zzdeg = zzdeg;
            zzdek = this.zzdek;
            this.zzdek = zzdek2;
            if (Math.abs(this.zzdek - zzdek) > 1.0E-4f) {
                this.zzcyg.getView().invalidate();
            }
            // monitorexit(this.lock)
            zzaoe.zzcvy.execute(new zzarn(this, zzdeg2, zzdeg, zzdei2, zzdei));
        }
    }
    
    public final void zza(final zzlr zzbuq) {
        synchronized (this.lock) {
            this.zzbuq = zzbuq;
        }
    }
    
    public final void zzb(final zzmu zzmu) {
        Object lock;
        String s;
        String s2;
        Label_0053_Outer:Label_0063_Outer:
        while (true) {
            lock = this.lock;
            while (true) {
            Label_0099:
                while (true) {
                Label_0093:
                    while (true) {
                        synchronized (lock) {
                            this.zzato = zzmu.zzato;
                            this.zzatp = zzmu.zzatp;
                            this.zzatq = zzmu.zzatq;
                            // monitorexit(lock)
                            if (zzmu.zzato) {
                                lock = "1";
                                if (!zzmu.zzatp) {
                                    break Label_0093;
                                }
                                s = "1";
                                if (zzmu.zzatq) {
                                    s2 = "1";
                                    this.zzf("initialState", CollectionUtils.mapOf((Object)"muteStart", lock, (Object)"customControlsRequested", (Object)s, (Object)"clickToExpandRequested", (Object)s2));
                                    return;
                                }
                                break Label_0099;
                            }
                        }
                        lock = "0";
                        continue Label_0053_Outer;
                    }
                    s = "0";
                    continue Label_0063_Outer;
                }
                s2 = "0";
                continue;
            }
        }
    }
    
    public final float zzim() {
        return this.zzdef;
    }
    
    public final float zzin() {
        synchronized (this.lock) {
            return this.zzdej;
        }
    }
    
    public final zzlr zzio() throws RemoteException {
        synchronized (this.lock) {
            return this.zzbuq;
        }
    }
}
