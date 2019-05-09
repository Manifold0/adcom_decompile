// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawa extends zzbbo<zzawa, zza> implements zzbcw
{
    private static volatile zzbdf<zzawa> zzakh;
    private static final zzawa zzdix;
    private int zzdir;
    private zzawc zzdiv;
    
    static {
        zzbbo.zza((Class)zzawa.class, (zzbbo)(zzdix = new zzawa()));
    }
    
    private zzawa() {
    }
    
    public static zzawa zzq(final zzbah zzbah) throws zzbbu {
        return (zzawa)zzbbo.zza((zzbbo)zzawa.zzdix, zzbah);
    }
    
    public final int getKeySize() {
        return this.zzdir;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawb.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawa();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawa.zzdix, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[] { "zzdiv", "zzdir" });
            }
            case 4: {
                return zzawa.zzdix;
            }
            case 5: {
                if ((zzakh = zzawa.zzakh) == null) {
                    synchronized (zzawa.class) {
                        zzbdf<zzawa> zzakh2;
                        if ((zzakh2 = zzawa.zzakh) == null) {
                            zzakh2 = (zzawa.zzakh = (zzbdf<zzawa>)new zzbbo$zzb((zzbbo)zzawa.zzdix));
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
    
    public final zzawc zzxe() {
        if (this.zzdiv == null) {
            return zzawc.zzxi();
        }
        return this.zzdiv;
    }
    
    public static final class zza extends zzbbo$zza<zzawa, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawa.zzdix);
        }
    }
}
