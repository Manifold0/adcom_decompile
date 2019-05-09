// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class zzc
{
    public byte[] data;
    public String zza;
    public long zzb;
    public long zzc;
    public long zzd;
    public long zze;
    public Map<String, String> zzf;
    public List<zzl> zzg;
    
    public zzc() {
        this.zzf = Collections.emptyMap();
    }
    
    public final boolean zzb() {
        return this.zzd < System.currentTimeMillis();
    }
}
