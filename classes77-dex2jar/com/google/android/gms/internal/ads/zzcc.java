// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import android.os.Build$VERSION;
import java.util.Random;
import android.os.ConditionVariable;

public class zzcc
{
    private static final ConditionVariable zzpt;
    protected static volatile zzhx zzpu;
    private static volatile Random zzpw;
    private zzcz zzps;
    protected volatile Boolean zzpv;
    
    static {
        zzpt = new ConditionVariable();
        zzcc.zzpu = null;
        zzcc.zzpw = null;
    }
    
    public zzcc(final zzcz zzps) {
        this.zzps = zzps;
        zzps.zzab().execute(new zzcd(this));
    }
    
    public static int zzx() {
        try {
            if (Build$VERSION.SDK_INT >= 21) {
                return ThreadLocalRandom.current().nextInt();
            }
            return zzy().nextInt();
        }
        catch (RuntimeException ex) {
            return zzy().nextInt();
        }
    }
    
    private static Random zzy() {
        Label_0028: {
            if (zzcc.zzpw != null) {
                break Label_0028;
            }
            synchronized (zzcc.class) {
                if (zzcc.zzpw == null) {
                    zzcc.zzpw = new Random();
                }
                return zzcc.zzpw;
            }
        }
    }
    
    public final void zza(final int n, final int n2, final long n3) throws IOException {
        try {
            zzcc.zzpt.block();
            if (this.zzpv && zzcc.zzpu != null) {
                final zzaw zzaw = new zzaw();
                zzaw.zzco = this.zzps.zzrt.getPackageName();
                zzaw.zzcp = n3;
                final zzhz zzd = zzcc.zzpu.zzd(zzbfi.zzb((zzbfi)zzaw));
                zzd.zzr(n2);
                zzd.zzs(n);
                zzd.zzbd();
            }
        }
        catch (Exception ex) {}
    }
}
