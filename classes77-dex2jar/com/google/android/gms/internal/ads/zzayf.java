// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzayf extends zzbbo<zzayf, zza> implements zzbcw
{
    private static volatile zzbdf<zzayf> zzakh;
    private static final zzayf zzdmt;
    private int zzdlq;
    private String zzdmr;
    private zzbbt<zzaxp> zzdms;
    
    static {
        zzbbo.zza((Class)zzayf.class, (zzbbo)(zzdmt = new zzayf()));
    }
    
    private zzayf() {
        this.zzdmr = "";
        this.zzdms = (zzbbt<zzaxp>)zzadd();
    }
    
    private final void zza(final zzaxp zzaxp) {
        if (zzaxp == null) {
            throw new NullPointerException();
        }
        if (!this.zzdms.zzaay()) {
            final zzbbt<zzaxp> zzdms = this.zzdms;
            final int size = zzdms.size();
            int n;
            if (size == 0) {
                n = 10;
            }
            else {
                n = size << 1;
            }
            this.zzdms = (zzbbt<zzaxp>)zzdms.zzbm(n);
        }
        this.zzdms.add((Object)zzaxp);
    }
    
    public static zza zzaam() {
        return (zza)zzayf.zzdmt.zza(zzbbo$zze.zzdue, null, null);
    }
    
    private final void zzei(final String zzdmr) {
        if (zzdmr == null) {
            throw new NullPointerException();
        }
        this.zzdmr = zzdmr;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzayg.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzayf();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzayf.zzdmt, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001\u0208\u0002\u001b", new Object[] { "zzdlq", "zzdmr", "zzdms", zzaxp.class });
            }
            case 4: {
                return zzayf.zzdmt;
            }
            case 5: {
                if ((zzakh = zzayf.zzakh) == null) {
                    synchronized (zzayf.class) {
                        zzbdf<zzayf> zzakh2;
                        if ((zzakh2 = zzayf.zzakh) == null) {
                            zzakh2 = (zzayf.zzakh = (zzbdf<zzayf>)new zzbbo$zzb((zzbbo)zzayf.zzdmt));
                        }
                        return zzakh2;
                    }
                    return 1;
                }
                break;
            }
            case 6: {
                return 1;
            }
            case 7: {
                return null;
            }
        }
        return zzakh;
    }
    
    public final List<zzaxp> zzaal() {
        return (List<zzaxp>)this.zzdms;
    }
    
    public static final class zza extends zzbbo$zza<zzayf, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzayf.zzdmt);
        }
        
        public final zza zzb(final zzaxp zzaxp) {
            this.zzadh();
            ((zzayf)this.zzdtx).zza(zzaxp);
            return this;
        }
        
        public final zza zzej(final String s) {
            this.zzadh();
            ((zzayf)this.zzdtx).zzei(s);
            return this;
        }
    }
}
