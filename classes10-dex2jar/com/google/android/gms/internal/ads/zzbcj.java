// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbcj implements zzbdn
{
    private static final zzbct zzdvw;
    private final zzbct zzdvv;
    
    static {
        zzdvw = new zzbck();
    }
    
    public zzbcj() {
        this(new zzbcl(new zzbct[] { zzbbn.zzadc(), zzaea() }));
    }
    
    private zzbcj(final zzbct zzbct) {
        this.zzdvv = zzbbq.zza(zzbct, "messageInfoFactory");
    }
    
    private static boolean zza(final zzbcs zzbcs) {
        return zzbcs.zzaeh() == zzbbo.zze.zzdui;
    }
    
    private static zzbct zzaea() {
        try {
            return (zzbct)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
        }
        catch (Exception ex) {
            return zzbcj.zzdvw;
        }
    }
    
    @Override
    public final <T> zzbdm<T> zzd(final Class<T> clazz) {
        zzbdo.zzf(clazz);
        final zzbcs zzb = this.zzdvv.zzb(clazz);
        if (zzb.zzaei()) {
            if (zzbbo.class.isAssignableFrom(clazz)) {
                return (zzbdm<T>)zzbda.zza(zzbdo.zzafp(), zzbbf.zzact(), zzb.zzaej());
            }
            return (zzbdm<T>)zzbda.zza(zzbdo.zzafn(), zzbbf.zzacu(), zzb.zzaej());
        }
        else if (zzbbo.class.isAssignableFrom(clazz)) {
            if (zza(zzb)) {
                return zzbcy.zza(clazz, zzb, zzbde.zzaem(), zzbce.zzadz(), zzbdo.zzafp(), zzbbf.zzact(), zzbcr.zzaef());
            }
            return zzbcy.zza(clazz, zzb, zzbde.zzaem(), zzbce.zzadz(), zzbdo.zzafp(), null, zzbcr.zzaef());
        }
        else {
            if (zza(zzb)) {
                return zzbcy.zza(clazz, zzb, zzbde.zzael(), zzbce.zzady(), zzbdo.zzafn(), zzbbf.zzacu(), zzbcr.zzaee());
            }
            return zzbcy.zza(clazz, zzb, zzbde.zzael(), zzbce.zzady(), zzbdo.zzafo(), null, zzbcr.zzaee());
        }
    }
}
