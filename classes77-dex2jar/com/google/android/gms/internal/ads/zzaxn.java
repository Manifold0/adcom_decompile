// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxn extends zzbbo<zzaxn, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxn> zzakh;
    private static final zzaxn zzdlk;
    private String zzdks;
    private zzbah zzdkt;
    private int zzdlj;
    
    static {
        zzbbo.zza((Class)zzaxn.class, (zzbbo)(zzdlk = new zzaxn()));
    }
    
    private zzaxn() {
        this.zzdks = "";
        this.zzdkt = zzbah.zzdpq;
    }
    
    public static zzaxn zzzc() {
        return zzaxn.zzdlk;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxo.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxn();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxn.zzdlk, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u0208\u0002\n\u0003\f", new Object[] { "zzdks", "zzdkt", "zzdlj" });
            }
            case 4: {
                return zzaxn.zzdlk;
            }
            case 5: {
                if ((zzakh = zzaxn.zzakh) == null) {
                    synchronized (zzaxn.class) {
                        zzbdf<zzaxn> zzakh2;
                        if ((zzakh2 = zzaxn.zzakh) == null) {
                            zzakh2 = (zzaxn.zzakh = (zzbdf<zzaxn>)new zzbbo$zzb((zzbbo)zzaxn.zzdlk));
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
    
    public final String zzyw() {
        return this.zzdks;
    }
    
    public final zzbah zzyx() {
        return this.zzdkt;
    }
    
    public static final class zza extends zzbbo$zza<zzaxn, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxn.zzdlk);
        }
    }
}
