// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import java.util.HashSet;
import java.util.Set;

final class zzg
{
    private final Component<?> zza;
    private final Set<zzg> zzb;
    private final Set<zzg> zzc;
    
    zzg(final Component<?> zza) {
        this.zzb = new HashSet<zzg>();
        this.zzc = new HashSet<zzg>();
        this.zza = zza;
    }
    
    final Set<zzg> zza() {
        return this.zzb;
    }
    
    final void zza(final zzg zzg) {
        this.zzb.add(zzg);
    }
    
    final Component<?> zzb() {
        return this.zza;
    }
    
    final void zzb(final zzg zzg) {
        this.zzc.add(zzg);
    }
    
    final void zzc(final zzg zzg) {
        this.zzc.remove(zzg);
    }
    
    final boolean zzc() {
        return this.zzc.isEmpty();
    }
    
    final boolean zzd() {
        return this.zzb.isEmpty();
    }
}
