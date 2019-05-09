// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaww extends zzbbo<zzaww, zza> implements zzbcw
{
    private static volatile zzbdf<zzaww> zzakh;
    private static final zzaww zzdjx;
    private int zzdju;
    private int zzdjv;
    private zzbah zzdjw;
    
    static {
        zzbbo.zza((Class)zzaww.class, (zzbbo)(zzdjx = new zzaww()));
    }
    
    private zzaww() {
        this.zzdjw = zzbah.zzdpq;
    }
    
    public static zzaww zzyk() {
        return zzaww.zzdjx;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawx.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaww();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaww.zzdjx, "\u0000\u0003\u0000\u0000\u0001\u000b\u000b\f\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[] { "zzdju", "zzdjv", "zzdjw" });
            }
            case 4: {
                return zzaww.zzdjx;
            }
            case 5: {
                if ((zzakh = zzaww.zzakh) == null) {
                    synchronized (zzaww.class) {
                        zzbdf<zzaww> zzakh2;
                        if ((zzakh2 = zzaww.zzakh) == null) {
                            zzakh2 = (zzaww.zzakh = (zzbdf<zzaww>)new zzbbo$zzb((zzbbo)zzaww.zzdjx));
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
    
    public final zzawy zzyh() {
        zzawy zzawy;
        if ((zzawy = com.google.android.gms.internal.ads.zzawy.zzat(this.zzdju)) == null) {
            zzawy = com.google.android.gms.internal.ads.zzawy.zzdkd;
        }
        return zzawy;
    }
    
    public final zzaxa zzyi() {
        zzaxa zzaxa;
        if ((zzaxa = com.google.android.gms.internal.ads.zzaxa.zzau(this.zzdjv)) == null) {
            zzaxa = com.google.android.gms.internal.ads.zzaxa.zzdkk;
        }
        return zzaxa;
    }
    
    public final zzbah zzyj() {
        return this.zzdjw;
    }
    
    public static final class zza extends zzbbo$zza<zzaww, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaww.zzdjx);
        }
    }
}
