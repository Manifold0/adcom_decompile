package com.google.firebase.components;

import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final class zzg {
    private final Component<?> zza;
    private final Set<zzg> zzb = new HashSet();
    private final Set<zzg> zzc = new HashSet();

    zzg(Component<?> component) {
        this.zza = component;
    }

    final void zza(zzg zzg) {
        this.zzb.add(zzg);
    }

    final void zzb(zzg zzg) {
        this.zzc.add(zzg);
    }

    final Set<zzg> zza() {
        return this.zzb;
    }

    final void zzc(zzg zzg) {
        this.zzc.remove(zzg);
    }

    final Component<?> zzb() {
        return this.zza;
    }

    final boolean zzc() {
        return this.zzc.isEmpty();
    }

    final boolean zzd() {
        return this.zzb.isEmpty();
    }
}
