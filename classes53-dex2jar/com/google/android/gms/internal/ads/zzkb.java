// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

@zzadh
public final class zzkb
{
    private static final Object sLock;
    private static zzkb zzarz;
    private final zzamu zzasa;
    private final zzjr zzasb;
    private final String zzasc;
    private final zzng zzasd;
    private final zznh zzase;
    private final zzni zzasf;
    
    static {
        sLock = new Object();
        final zzkb zzarz = new zzkb();
        synchronized (zzkb.sLock) {
            zzkb.zzarz = zzarz;
        }
    }
    
    protected zzkb() {
        this.zzasa = new zzamu();
        this.zzasb = new zzjr(new zzjh(), new zzjg(), new zzme(), new zzrv(), new zzahi(), new zzaao(), new zzrw());
        this.zzasc = zzamu.zzsi();
        this.zzasd = new zzng();
        this.zzase = new zznh();
        this.zzasf = new zzni();
    }
    
    private static zzkb zzie() {
        synchronized (zzkb.sLock) {
            return zzkb.zzarz;
        }
    }
    
    public static zzamu zzif() {
        return zzie().zzasa;
    }
    
    public static zzjr zzig() {
        return zzie().zzasb;
    }
    
    public static String zzih() {
        return zzie().zzasc;
    }
    
    public static zznh zzii() {
        return zzie().zzase;
    }
    
    public static zzng zzij() {
        return zzie().zzasd;
    }
    
    public static zzni zzik() {
        return zzie().zzasf;
    }
}
