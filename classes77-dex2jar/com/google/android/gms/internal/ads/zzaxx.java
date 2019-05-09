// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxx extends zzbbo<zzaxx, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxx> zzakh;
    private static final zzaxx zzdme;
    private String zzdmd;
    
    static {
        zzbbo.zza((Class)zzaxx.class, (zzbbo)(zzdme = new zzaxx()));
    }
    
    private zzaxx() {
        this.zzdmd = "";
    }
    
    public static zzaxx zzaac() {
        return zzaxx.zzdme;
    }
    
    public static zzaxx zzak(final zzbah zzbah) throws zzbbu {
        return (zzaxx)zzbbo.zza((zzbbo)zzaxx.zzdme, zzbah);
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxy.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxx();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxx.zzdme, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\u0208", new Object[] { "zzdmd" });
            }
            case 4: {
                return zzaxx.zzdme;
            }
            case 5: {
                if ((zzakh = zzaxx.zzakh) == null) {
                    synchronized (zzaxx.class) {
                        zzbdf<zzaxx> zzakh2;
                        if ((zzakh2 = zzaxx.zzakh) == null) {
                            zzakh2 = (zzaxx.zzakh = (zzbdf<zzaxx>)new zzbbo$zzb((zzbbo)zzaxx.zzdme));
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
    
    public final String zzaab() {
        return this.zzdmd;
    }
    
    public static final class zza extends zzbbo$zza<zzaxx, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxx.zzdme);
        }
    }
}
