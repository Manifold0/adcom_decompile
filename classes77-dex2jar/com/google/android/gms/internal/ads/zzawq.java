// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawq extends zzbbo<zzawq, zza> implements zzbcw
{
    private static volatile zzbdf<zzawq> zzakh;
    private static final zzawq zzdjo;
    private zzaww zzdjl;
    private zzawm zzdjm;
    private int zzdjn;
    
    static {
        zzbbo.zza((Class)zzawq.class, (zzbbo)(zzdjo = new zzawq()));
    }
    
    private zzawq() {
    }
    
    public static zzawq zzxx() {
        return zzawq.zzdjo;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawr.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawq();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawq.zzdjo, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[] { "zzdjl", "zzdjm", "zzdjn" });
            }
            case 4: {
                return zzawq.zzdjo;
            }
            case 5: {
                if ((zzakh = zzawq.zzakh) == null) {
                    synchronized (zzawq.class) {
                        zzbdf<zzawq> zzakh2;
                        if ((zzakh2 = zzawq.zzakh) == null) {
                            zzakh2 = (zzawq.zzakh = (zzbdf<zzawq>)new zzbbo$zzb((zzbbo)zzawq.zzdjo));
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
    
    public final zzaww zzxu() {
        if (this.zzdjl == null) {
            return zzaww.zzyk();
        }
        return this.zzdjl;
    }
    
    public final zzawm zzxv() {
        if (this.zzdjm == null) {
            return zzawm.zzxq();
        }
        return this.zzdjm;
    }
    
    public final zzawk zzxw() {
        zzawk zzawk;
        if ((zzawk = com.google.android.gms.internal.ads.zzawk.zzaq(this.zzdjn)) == null) {
            zzawk = com.google.android.gms.internal.ads.zzawk.zzdjf;
        }
        return zzawk;
    }
    
    public static final class zza extends zzbbo$zza<zzawq, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawq.zzdjo);
        }
    }
}
